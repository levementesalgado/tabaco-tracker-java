<!DOCTYPE html>
<html lang="pt-BR" class="bg-zinc-950 text-zinc-100">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?= htmlspecialchars($title ?? 'Tabaco Tracker') ?> — Tabaco Tracker</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>tailwind.config={theme:{extend:{colors:{tobacco:{50:'#fdf8f0',100:'#f9edda',200:'#f2d9b3',300:'#e9c28a',400:'#e0a85e',500:'#d48d3c',600:'#b8732f',700:'#9a5a28',800:'#7e4824',900:'#6a3d21',950:'#3a1f10'}}}}}</script>
</head>
<body class="min-h-screen">
    <nav class="border-b border-zinc-800 px-6 py-4 flex items-center gap-6">
        <a href="/" class="text-xl font-bold text-tobacco-400">Tabaco Tracker</a>
        <a href="/marcas" class="hover:text-tobacco-400">Marcas</a>
        <a href="/leaderboard" class="hover:text-tobacco-400">Ranking</a>
        <a href="/tracker" class="hover:text-tobacco-400">Tracker</a>
        <a href="/perfil" class="hover:text-tobacco-400">Perfil</a>
        <div class="ml-auto flex gap-3">
            <a href="/auth/login" class="px-4 py-2 rounded bg-zinc-800 hover:bg-zinc-700">Entrar</a>
            <a href="/auth/cadastro" class="px-4 py-2 rounded bg-tobacco-600 hover:bg-tobacco-500 text-white">Cadastrar</a>
        </div>
    </nav>
    <main class="max-w-6xl mx-auto px-6 py-8">
