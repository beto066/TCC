package br.unitins.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileService {

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "usuario" + File.separator;

    private final String PATH_PRODUTO = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "produto" + File.separator;

    private final String PATH_PARCEIRO = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "parceiro" + File.separator;

    private final String PATH_EVENTO = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "evento" + File.separator;

    private final String PATH_ESPORTES = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "esporte" + File.separator;

    // private final String PATH_USER = System.getProperty("user.dir")
    // + File.separator + "img" + File.separator;

    public String salvarImagemUsuario(byte[] imagem, String nomeImagem) throws IOException {
        // goku.png
        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif");
        // senao contem gera um exception
        if (!listMimeType.contains(mimeType))
            throw new IOException("File no suported.");

        // ser for maior que 10mb, gerar um exception
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("File is too big.");

        // criando as pastas caso nao exista
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando um nome randomico com a extensao
        String nomeArquivo = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // o correto é verificar se existe e caso exista gerar outra numeracao
        if (file.exists())
            throw new IOException("This file already exists.");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        // salvando no hd
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        // System.out.println(path);

        return nomeArquivo;

    }

    public File download(String nomeImagem) {
        File file = new File(PATH_USER + nomeImagem);
        return file;
    }

    public String salvarImagemProduto(byte[] imagem, String nomeImagem) throws IOException {
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif");
        // senao contem gera um exception
        if (!listMimeType.contains(mimeType))
            throw new IOException("File no suported.");

        // ser for maior que 10mb, gerar um exception
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("File is too big.");

        // criando as pastas caso nao exista
        File diretorio = new File(PATH_PRODUTO);
        if (!diretorio.exists())
            diretorio.mkdirs();

        String nomeArquivo = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String path = PATH_PRODUTO + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // o correto é verificar se existe e caso exista gerar outra numeracao
        if (file.exists())
            throw new IOException("This file already exists.");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        // salvando no hd
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    public File downloadImagemProduto(String nomeImagem) {
        File file = new File(PATH_PRODUTO + nomeImagem);
        return file;
    }

    public String salvarImagemEvento(byte[] imagem, String nomeImagem) throws IOException {
        // goku.png
        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif");
        // senao contem gera um exception
        if (!listMimeType.contains(mimeType))
            throw new IOException("File no suported.");

        // ser for maior que 10mb, gerar um exception
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("File is too big.");

        // criando as pastas caso nao exista
        File diretorio = new File(PATH_EVENTO);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando um nome randomico com a extensao
        String nomeArquivo = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);

        String path = PATH_EVENTO + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // o correto é verificar se existe e caso exista gerar outra numeracao
        if (file.exists())
            throw new IOException("This file already exists.");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        // salvando no hd
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    public File downloadImagemEvento(String nomeImagem) {
        File file = new File(PATH_EVENTO + nomeImagem);
        return file;
    }


    public String salvarImagemParceiro(byte[] imagem, String nomeImagem) throws IOException {
        // goku.png
        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif");
        // senao contem gera um exception
        if (!listMimeType.contains(mimeType))
            throw new IOException("File no suported.");

        // ser for maior que 10mb, gerar um exception
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("File is too big.");

        // criando as pastas caso nao exista
        File diretorio = new File(PATH_PARCEIRO);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando um nome randomico com a extensao
        String nomeArquivo = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);

        String path = PATH_PARCEIRO + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // o correto é verificar se existe e caso exista gerar outra numeracao
        if (file.exists())
            throw new IOException("This file already exists.");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        // salvando no hd
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    public File downloadImagemParceiro(String nomeImagem) {
        File file = new File(PATH_PARCEIRO + nomeImagem);
        return file;
    }

    public String salvarImagemEsporte(byte[] imagem, String nomeImagem) throws IOException {
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/png", "image/gif");
        // senao contem gera um exception
        if (!listMimeType.contains(mimeType))
            throw new IOException("File no suported.");

        // ser for maior que 10mb, gerar um exception
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("File is too big.");

        // criando as pastas caso nao exista
        File diretorio = new File(PATH_ESPORTES);
        if (!diretorio.exists())
            diretorio.mkdirs();

        String nomeArquivo = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String path = PATH_ESPORTES + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // o correto é verificar se existe e caso exista gerar outra numeracao
        if (file.exists())
            throw new IOException("This file already exists.");

        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        // salvando no hd
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    public File downloadImagemEsporte(String nomeImagem) {
        File file = new File(PATH_ESPORTES + nomeImagem);
        return file;
    }

}