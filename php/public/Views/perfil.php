<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-6">Meu Perfil</h1>
<form class="max-w-md space-y-4" id="perfil-form">
    <div><label class="block text-sm text-zinc-400 mb-1">Nome</label><input name="nome" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-full"></div>
    <div><label class="block text-sm text-zinc-400 mb-1">Média de cigarros/dia</label><input name="mediaCigarrosDia" type="number" min="0" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-full"></div>
    <div><label class="block text-sm text-zinc-400 mb-1">Região</label><input name="regiao" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-full"></div>
    <button type="submit" class="px-6 py-2 bg-tobacco-600 rounded hover:bg-tobacco-500 font-semibold">Salvar</button>
</form>
<script>
fetch('/api/perfil',{headers:{'Authorization':'Bearer '+localStorage.getItem('token')}}).then(r=>r.json()).then(d=>{
    if(!d)return; Object.keys(d).forEach(k=>{const i=document.querySelector(`[name="${k}"]`);if(i)i.value=d[k];});
});
document.getElementById('perfil-form').addEventListener('submit',async e=>{
    e.preventDefault(); const f=new FormData(e.target),o={};
    f.forEach((v,k)=>o[k]=v);
    await fetch('/api/perfil',{method:'PUT',headers:{'Content-Type':'application/json','Authorization':'Bearer '+localStorage.getItem('token')},body:JSON.stringify(o)});
    alert('Salvo!');
});
</script>
<?php include __DIR__ . '/layout_bottom.php'; ?>
