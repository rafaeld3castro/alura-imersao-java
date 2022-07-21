public enum ExtratorEnum {
    
    IMDB("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json", new ExtratorDeConteudoDoIMDb()),
    NASA("https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json", new ExtratorDeConteudoDaNasa());

    private final String url;
    private final ExtratorDeConteudo extrator;

    private ExtratorEnum(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }
}
