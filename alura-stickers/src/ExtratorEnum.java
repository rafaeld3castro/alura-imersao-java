public enum ExtratorEnum {

    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json",
            new ExtratorDeConteudoDoIMDb()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json",
            new ExtratorDeConteudoDaNasa()),
    LINGUAGENS("http://localhost:8080/linguagens",
            new ExtratorDeConteudoLinguagens());

    private final String url;
    private final ExtratorDeConteudo extrator;

    private ExtratorEnum(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String url() {
        return url;
    }

    public ExtratorDeConteudo extrator() {
        return extrator;
    }
}
