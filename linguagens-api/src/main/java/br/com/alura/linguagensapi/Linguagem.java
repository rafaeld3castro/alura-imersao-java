package br.com.alura.linguagensapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

@Document(collection = "principaisLinguagens")
public class Linguagem {

    @Id
    private String id;

    @Field("title")
    private String name;

    private String image;

    private Integer ranking;

    public Linguagem() {
    }

    public Linguagem(String name, String image, int ranking) {
        this.name = name;
        this.image = image;
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getRanking() {
        return ranking;
    }

    public Linguagem atualizar(Linguagem linguagem) {
        if (StringUtils.hasLength(linguagem.getName())) {
            this.name = linguagem.getName();
        }
        if (StringUtils.hasLength(linguagem.getImage())) {
            this.image = linguagem.getImage();
        }
        if (linguagem.getRanking() != null) {
            this.ranking = linguagem.getRanking();
        }
        return this;
    }

}
