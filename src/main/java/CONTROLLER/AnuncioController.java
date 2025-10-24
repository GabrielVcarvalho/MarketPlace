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

    public void curtirAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
        feedBackService.like(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
    }
}