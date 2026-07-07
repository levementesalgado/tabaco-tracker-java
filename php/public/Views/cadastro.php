<?php include __DIR__ . '/layout_top.php'; ?>
<h1 class="text-2xl font-bold mb-6">Cadastro</h1>
<form class="max-w-sm space-y-4" id="cadastro-form">
    <div><label class="block text-sm text-zinc-400 mb-1">Email</label><input type="email" name="email" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-full" required></div>
    <div><label class="block text-sm text-zinc-400 mb-1">Senha</label><input type="password" name="password" class="bg-zinc-900 border border-zinc-700 rounded px-4 py-2 w-full" required></div>
    <button type="submit" class="px-6 py-2 bg-tobacco-600 rounded hover:bg-tobacco-500 font-semibold">Criar Conta</button>
</form>
<script>
document.getElementById('cadastro-form').addEventListener('submit',async e=>{
    e.preventDefault(); const f=new FormData(e.target);
    const r=await fetch('https://YOUR_SUPABASE_PROJECT.supabase.co/auth/v1/signup',{
        method:'POST',headers:{'Content-Type':'application/json','apikey':'YOUR_SUPABASE_ANON_KEY'},
        body:JSON.stringify({email:f.get('email'),password:f.get('password')})
    });
    const d=await r.json();
    if(d.access_token){localStorage.setItem('token',d.access_token);localStorage.setItem('supabase_user',JSON.stringify(d.user));window.location='/tracker';}
    else alert('Erro no cadastro');
});
</script>
<?php include __DIR__ . '/layout_bottom.php'; ?>
