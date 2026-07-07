<?php

require __DIR__ . '/../vendor/autoload.php';

use Slim\Factory\AppFactory;
use App\Middleware\AuthMiddleware;
use GuzzleHttp\Client;

$dotenv = Dotenv\Dotenv::createImmutable(__DIR__ . '/..');
$dotenv->safeLoad();

$app = AppFactory::create();
$app->addBodyParsingMiddleware();
$app->addErrorMiddleware(true, true, true);

$apiUrl = $_ENV['API_URL'] ?? 'http://java-api:8080';

$client = new Client(['base_uri' => $apiUrl, 'http_errors' => false]);

$auth = new AuthMiddleware($_ENV['JWT_SECRET'] ?? 'dev-secret-change-in-prod');

// --- Páginas ---
$app->get('/', function ($request, $response) {
    return render($response, 'home', ['title' => 'Tabaco Tracker']);
});

$app->get('/marcas', function ($request, $response) use ($client) {
    $resp = $client->get('/api/v1/marcas');
    $marcas = json_decode($resp->getBody(), true) ?? [];
    return render($response, 'marcas', ['title' => 'Marcas', 'marcas' => $marcas]);
});

$app->get('/marcas/{slug}', function ($request, $response, $args) use ($client) {
    $resp = $client->get("/api/v1/marcas/slug/{$args['slug']}");
    $marca = json_decode($resp->getBody(), true);
    if (!$marca) return $response->withStatus(404);
    $avs = json_decode($client->get("/api/v1/avaliacoes/marca/{$marca['id']}")->getBody(), true) ?? [];
    return render($response, 'marca', ['title' => $marca['nome'], 'marca' => $marca, 'avaliacoes' => $avs]);
});

$app->get('/leaderboard', function ($request, $response) use ($client) {
    $resp = $client->get('/api/v1/leaderboard');
    $ranking = json_decode($resp->getBody(), true) ?? [];
    return render($response, 'leaderboard', ['title' => 'Ranking', 'ranking' => $ranking]);
});

$app->get('/tracker', function ($request, $response) {
    return render($response, 'tracker', ['title' => 'Meu Tracker']);
});

$app->get('/perfil', function ($request, $response) {
    return render($response, 'perfil', ['title' => 'Meu Perfil']);
});

$app->get('/auth/login', function ($request, $response) {
    return render($response, 'login', ['title' => 'Login']);
});

$app->get('/auth/cadastro', function ($request, $response) {
    return render($response, 'cadastro', ['title' => 'Cadastro']);
});

// --- API Proxy (protegido) ---
$app->group('/api', function ($group) use ($client) {
    $group->any('/{routes:.+}', function ($request, $response, $args) use ($client) {
        $method = strtolower($request->getMethod());
        $path = '/api/v1/' . $args['routes'];
        $opts = [];

        if (in_array($method, ['post', 'put', 'patch'])) {
            $opts['json'] = $request->getParsedBody();
        }

        $userId = $request->getAttribute('userId');
        if ($userId) {
            $token = $request->getHeaderLine('Authorization');
            $opts['headers'] = ['Authorization' => $token, 'Content-Type' => 'application/json'];
        }

        $resp = $client->$method($path, $opts);
        $body = $resp->getBody()->getContents();
        $newResp = $response->withStatus($resp->getStatusCode());
        $newResp->getBody()->write($body);
        return $newResp->withHeader('Content-Type', 'application/json');
    });
})->add($auth);

function render($response, $template, $data = []) {
    extract($data);
    ob_start();
    include __DIR__ . "/Views/{$template}.php";
    $html = ob_get_clean();
    $response->getBody()->write($html);
    return $response;
}

$app->run();
