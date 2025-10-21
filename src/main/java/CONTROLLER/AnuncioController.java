package CONTROLLER;

import DAO.AnuncioDAO;
import DTO.AnuncioDTO;
import REPOSITORY.AnuncioRepository;
import SERVICE.Anuncio.AnuncioService;
import io.javalin.http.Context;

public class AnuncioController {
    private final AnuncioService anuncioService;

    public AnuncioController(
            AnuncioService anuncioService
    ) {
        this.anuncioService = anuncioService;
    }

    public void criarAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);
        anuncioService.verificarCriacaoAnuncio(anuncioDTO);
        anuncioService.criarAnuncio(anuncioDTO);
    }
}