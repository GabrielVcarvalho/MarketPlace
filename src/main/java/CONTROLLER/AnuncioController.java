package CONTROLLER;

import DTO.AnuncioDTO;
import SERVICE.Anuncio.AnuncioService;
import SERVICE.Anuncio.FeedBackService;
import io.javalin.http.Context;

public class AnuncioController {
    private final AnuncioService anuncioService;
    private final FeedBackService feedBackService;

    public AnuncioController(
            AnuncioService anuncioService,
            FeedBackService feedBackService
    ) {
        this.anuncioService = anuncioService;
        this.feedBackService = feedBackService;
    }

    public void criarAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
        anuncioService.verificarCriacaoAnuncio(anuncioDTO);
        anuncioService.criarAnuncio(anuncioDTO);
    }

    public void likeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
        try{
            feedBackService.like(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        } catch (RuntimeException e) {
            context.status(400);
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deslikeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
        try{
            feedBackService.deslike(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        } catch (RuntimeException e) {
            context.status(400);
            throw new RuntimeException(e.getMessage());
        }
    }
}