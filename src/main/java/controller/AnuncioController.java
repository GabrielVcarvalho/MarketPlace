package controller;

import dto.AnuncioDTO;
import dto.AvaliacaoDTO;
import dto.ComentarioDTO;
import io.javalin.http.UnauthorizedResponse;
import service.anuncio.AnuncioService;
import service.anuncio.ComentarioService;
import service.anuncio.exceptions.*;
import service.anuncio.FeedBackService;
import io.javalin.http.Context;
import service.exceptions.NullDTO;
import service.exceptions.TriedConvertNullDTOForEntity;
import service.mapper.exceptions.NullMapperObject;
import service.usuario.exceptions.InvalidRole;
import service.usuario.exceptions.UnauthorizedRole;

public class AnuncioController {
    private final AnuncioService anuncioService;
    private final FeedBackService feedBackService;
    private final ComentarioService comentarioService;

    public AnuncioController(
            AnuncioService anuncioService,
            FeedBackService feedBackService,
            ComentarioService comentarioService
    ) {
        this.anuncioService = anuncioService;
        this.feedBackService = feedBackService;
        this.comentarioService = comentarioService;
    }

    void criarAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            anuncioService.verificarCriacaoAnuncio(anuncioDTO);
            anuncioService.criarAnuncio(anuncioDTO);
            context.status(201);
        }
        catch (InvalidSellerId | TitleOfAdNotExits e){
            context.status(404)
                    .result(e.getMessage());
        }
        catch (InvalidRole | UnauthorizedRole e){
            throw new UnauthorizedResponse(e.getMessage());
        }
    }

    void likeAnuncio(Context context){
        AvaliacaoDTO avaliacaoDTO;

        try{
            avaliacaoDTO = context.bodyAsClass(AvaliacaoDTO.class);
            feedBackService.like(avaliacaoDTO);
            context.status(200);
        }
        catch (NullDTO | IllegalArgumentException e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (AdNotExists | InvalidSellerId e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void deslikeAnuncio(Context context){
        AvaliacaoDTO avaliacaoDTO;

        try{
            avaliacaoDTO = context.bodyAsClass(AvaliacaoDTO.class);
            feedBackService.deslike(avaliacaoDTO);
            context.status(200);
        }
        catch (NullDTO | IllegalArgumentException e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (AdNotExists | InvalidSellerId e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void lerAnuncioPeloId(Context context){
        try{
            AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
            context.json(anuncioService.lerAnuncioPeloId(anuncioDTO));
        }
        catch (NumberFormatException e) {
            context.status(400)
                    .result("O id informado não é um número");
        }
        catch (IllegalArgumentException e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (AnuncioIdNotExists e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void lerAnuncioPeloTitulo(Context context){
        try{
            AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
            context.json(anuncioService.lerAnuncioPeloTitulo(anuncioDTO));
        }
        catch (IllegalArgumentException e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (TitleOfAdNotExits e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void adicionarComentario(Context context){
        try{
            ComentarioDTO comentarioDTO = context.bodyAsClass(ComentarioDTO.class);
            comentarioService.verificarCriacaoComentario(comentarioDTO);
            comentarioService.criarComentario(comentarioDTO);
            context.status(201);
        }
        catch (NullDTO | NullContentInComment e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (AnuncioIdNotExists | UserIdNotExists e){
            context.status(404)
                    .result(e.getMessage());
        }
        catch (TriedConvertNullDTOForEntity e){
            context.status(500)
                    .result(e.getMessage());
        }
    }

    void lerTodosOsAnuncios(Context context){
        try{
            anuncioService.lerTodosOsAnuncios();
        }
        catch (InternalErrorToConvertToDTO e) {
            context.status(500);
        }
    }
}