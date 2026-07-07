package com.tabacotracker.service;

import com.tabacotracker.dto.TagSugerirDTO;
import com.tabacotracker.dto.TagVotarDTO;
import com.tabacotracker.model.MarcasTag;
import com.tabacotracker.model.Tag;
import com.tabacotracker.model.VotosTag;
import com.tabacotracker.repository.MarcasTagRepository;
import com.tabacotracker.repository.TagRepository;
import com.tabacotracker.repository.VotosTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepo;
    private final MarcasTagRepository mtRepo;
    private final VotosTagRepository vtRepo;

    public Tag sugerir(TagSugerirDTO d, UUID userId) {
        Tag t = tagRepo.findByNome(d.getNome())
            .orElseGet(() -> tagRepo.save(Tag.builder().nome(d.getNome()).build()));
        mtRepo.save(MarcasTag.builder()
            .marcaId(d.getMarcaId()).tagId(t.getId())
            .sugeridoPor(userId).status("approved").build());
        return t;
    }

    public Map<String, Object> votar(TagVotarDTO d, UUID userId) {
        var existente = vtRepo.findByMarcasTagIdAndUsuarioId(d.getMarcasTagId(), userId);
        if (existente.isPresent()) {
            if (existente.get().getVoto().equals(d.getVoto())) return Map.of("ok", true);
            existente.get().setVoto(d.getVoto());
            vtRepo.save(existente.get());
        } else {
            vtRepo.save(VotosTag.builder()
                .marcasTagId(d.getMarcasTagId()).usuarioId(userId).voto(d.getVoto()).build());
        }

        var votos = vtRepo.findByMarcasTagId(d.getMarcasTagId());
        int up = (int) votos.stream().filter(v -> v.getVoto() == 1).count();
        int down = (int) votos.stream().filter(v -> v.getVoto() == -1).count();
        mtRepo.findById(d.getMarcasTagId()).ifPresent(mt -> {
            mt.setTotalUpvotes(up);
            mt.setTotalDownvotes(down);
            mtRepo.save(mt);
        });
        return Map.of("ok", true, "upvotes", up, "downvotes", down);
    }
}
