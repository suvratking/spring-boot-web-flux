package com.king.ex.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", nullable = false, updatable = false, columnDefinition = "smallint")
    private Integer languageId;

    @Column(name = "name", nullable = false, columnDefinition = "char(20)")
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Film> films;

    @ManyToMany
    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer language_id) {
        this.languageId = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (!Objects.equals(languageId, language.languageId)) return false;
        return Objects.equals(name, language.name);
    }

    @Override
    public int hashCode() {
        int result = languageId != null ? languageId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
