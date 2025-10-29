package controller;

import dto.AnuncioDTO;
import io.javalin.http.UnauthorizedResponse;
import service.anuncio.AnuncioService;
import service.anuncio.exceptions.AdNotExists;
import service.anuncio.exceptions.AnuncioIdNotExists;
import service.anuncio.exceptions.InvalidSellerId;
import service.anuncio.exceptions.TitleOfAdNotExits;
import service.anuncio.FeedBackService;
import io.javalin.http.Context;
import service.exceptions.NullDTO;
import service.usuario.exceptions.InvalidRole;
import service.usuario.exceptions.UnauthorizedRole;

public class AnuncioController {
    private final AnuncioService anuncioService;
    private final FeedBackService feedBackService;

    AnuncioController(
            AnuncioService anuncioService,
            FeedBackService feedBackService
    ) {
        this.anuncioService = anuncioService;
        this.feedBackService = feedBackService;
    }

    void criarAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            anuncioService.verificarCriacaoAnuncio(anuncioDTO);
        }
        catch (InvalidSellerId | TitleOfAdNotExits e){
            context.status(404)
                    .result(e.getMessage());
            return;
        }
        catch (InvalidRole | UnauthorizedRole e){
            throw new UnauthorizedResponse(e.getMessage());
        }

        anuncioService.criarAnuncio(anuncioDTO);
        context.status(201);
    }

    void likeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            feedBackService.like(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        }
        catch (AdNotExists | InvalidSellerId e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void deslikeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            feedBackService.deslike(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        }
        catch (AdNotExists | InvalidSellerId e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void lerAnuncioPeloId(Context context){
        try{
            context.json(anuncioService.lerAnuncio(
                    Integer.parseInt(context.pathParam("id"))
            ));
        }
        catch (NumberFormatException e) {
            context.status(400)
                    .result("O id informado não é um número");
        }
        catch (IllegalArgumentException | NullDTO e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (AnuncioIdNotExists e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }

    void lerAnuncioPeloTitulo(Context context){
        String titulo;

        try{
            titulo = context.pathParam("titulo");
            context.json(anuncioService.lerAnuncio(titulo));
        }
        catch (IllegalArgumentException | NullDTO e){
            context.status(400)
                    .result(e.getMessage());
        }
        catch (TitleOfAdNotExits e) {
            context.status(404)
                    .result(e.getMessage());
        }
    }
}