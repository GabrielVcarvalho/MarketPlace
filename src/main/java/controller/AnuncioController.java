package controller;

import dto.AnuncioDTO;
import service.Anuncio.AnuncioService;
import service.Anuncio.Exceptions.AnuncioIdNotExists;
import service.Anuncio.Exceptions.TitleOfAdNotExits;
import service.Anuncio.FeedBackService;
import io.javalin.http.Context;

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
        anuncioService.verificarCriacaoAnuncio(anuncioDTO);
        anuncioService.criarAnuncio(anuncioDTO);
    }

    void likeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            feedBackService.like(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        } catch (RuntimeException e) {
            context.status(400);
            throw new RuntimeException(e.getMessage());
        }
    }

    void deslikeAnuncio(Context context){
        AnuncioDTO anuncioDTO = context.bodyAsClass(AnuncioDTO.class);

        try{
            feedBackService.deslike(anuncioDTO.getId(), anuncioDTO.getIdVendedor());
            context.status(200);
        } catch (RuntimeException e) {
            context.status(400);
        }
    }

    void lerAnuncioPeloId(Context context){
        int id;

        try{
            id = Integer.parseInt(context.pathParam("id"));
            context.json(anuncioService.lerAnuncio(id));
        }
        catch (NumberFormatException e) {
            context.status(400).result("O id informado não é um número");
        }
        catch (IllegalArgumentException e){
            context.status(400).result("id é nulo ou inválido");
        }
        catch (AnuncioIdNotExists e) {
            context.status(404).result("O id informado não existe");
        }
    }

    void lerAnuncioPeloTitulo(Context context){
        String titulo;

        try{
            titulo = context.pathParam("titulo");
            context.json(anuncioService.lerAnuncio(titulo));
        }
        catch (IllegalArgumentException e){
            context.status(400).result("Título é nulo");
        }
        catch (TitleOfAdNotExits e) {
            context.status(404).result("O titulo do anuncio informado não existe");
        }
    }
}