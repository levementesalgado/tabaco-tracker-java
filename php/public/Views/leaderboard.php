<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-6">Ranking</h1>
<div class="space-y-3">
    <?php foreach ($ranking ?? [] as $i => $m): ?>
        <div class="flex items-center gap-4 bg-zinc-900 border border-zinc-800 rounded-lg p-4">
            <span class="text-2xl font-bold text-tobacco-400 w-8">#<?= $i + 1 ?></span>
            <div class="flex-1">
                <a href="/marcas/<?= htmlspecialchars($m['slug']) ?>" class="font-semibold hover:text-tobacco-400"><?= htmlspecialchars($m['nome']) ?></a>
                <p class="text-zinc-400 text-sm"><?= $m['total_avaliacoes'] ?> avaliações · média <?= $m['media'] ?></p>
            </div>
            <span class="text-lg font-bold text-tobacco-400"><?= $m['media'] ?></span>
        </div>
    <?php endforeach; ?>
</div>
<?php include __DIR__ . '/layout_bottom.php'; ?>
