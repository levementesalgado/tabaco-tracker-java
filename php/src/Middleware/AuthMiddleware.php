<?php

namespace App\Middleware;

use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Server\RequestHandlerInterface as Handler;
use Psr\Http\Message\ResponseInterface as Response;
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

class AuthMiddleware
{
    private string $secret;

    public function __construct(string $secret)
    {
        $this->secret = $secret;
    }

    public function __invoke(Request $request, Handler $handler): Response
    {
        $auth = $request->getHeaderLine('Authorization');

        if ($auth && str_starts_with($auth, 'Bearer ')) {
            try {
                $decoded = JWT::decode(substr($auth, 7), new Key($this->secret, 'HS256'));
                $request = $request->withAttribute('userId', $decoded->sub);
            } catch (\Exception $e) {}
        }

        return $handler->handle($request);
    }
}
