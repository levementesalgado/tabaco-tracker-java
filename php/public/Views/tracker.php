<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-6">Meu Tracker</h1>
<p class="text-zinc-400">Registre quantos cigarros você fumou hoje.</p>
<form class="mt-4 flex gap-3 items-end">
    <div>
        <label class="block text-sm text-zinc-400 mb-1">Quantidade</label>
        <input type="number" name="quantidade" min="0" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-32" required>
    </div>
    <button type="submit" class="px-6 py-2 bg-tobacco-600 rounded hover:bg-tobacco-500 font-semibold">Salvar</button>
</form>
<div id="tracker-list" class="mt-8 space-y-2"></div>
<script>
fetch('/api/tracker').then(r=>r.json()).then(d=>{
    const el=document.getElementById('tracker-list');
    d.forEach(r=>{
        const div=document.createElement('div');
        div.className='bg-zinc-900 border border-zinc-800 rounded p-3 flex justify-between';
        div.innerHTML=`<span>${r.data}</span><span class="font-bold">${r.quantidade}</span>`;
        el.appendChild(div);
    });
});
document.querySelector('form').addEventListener('submit',async e=>{
    e.preventDefault(); const q=e.target.quantidade.value;
    await fetch('/api/tracker',{method:'POST',headers:{'Content-Type':'application/json','Authorization':'Bearer '+localStorage.getItem('token')},body:JSON.stringify({quantidade:+q})});
    location.reload();
});
</script>
<?php include __DIR__ . '/layout_bottom.php'; ?>
