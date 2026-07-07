<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-2"><?= htmlspecialchars($marca['nome']) ?></h1>
<?php if (!empty($marca['fabricante'])): ?><p class="text-zinc-400 mb-6"><?= htmlspecialchars($marca['fabricante']) ?></p><?php endif; ?>

<h2 class="text-xl font-semibold mb-4">Avaliações</h2>
<div class="space-y-4">
    <?php foreach ($avaliacoes ?? [] as $a): ?>
        <div class="bg-zinc-900 border border-zinc-800 rounded-lg p-4">
            <div class="flex items-center gap-2 mb-2">
                <span class="text-tobacco-400 font-bold text-lg"><?= $a['nota'] ?>/20</span>
                <?php if (!empty($a['preco'])): ?><span class="text-zinc-500">R$ <?= number_format($a['preco'], 2) ?></span><?php endif; ?>
                <?php if (!empty($a['regiao'])): ?><span class="text-zinc-500"><?= htmlspecialchars($a['regiao']) ?></span><?php endif; ?>
            </div>
            <?php if (!empty($a['comentario'])): ?><p class="text-zinc-300"><?= htmlspecialchars($a['comentario']) ?></p><?php endif; ?>
        </div>
    <?php endforeach; ?>
    <?php if (empty($avaliacoes)): ?><p class="text-zinc-500">Nenhuma avaliação ainda.</p><?php endif; ?>
</div>
<?php include __DIR__ . '/layout_bottom.php'; ?>
