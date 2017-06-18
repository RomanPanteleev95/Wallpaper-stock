package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "categories_default_wallpapers", joinColumns = @JoinColumn(name = "categories_id"), inverseJoinColumns = @JoinColumn(name = "default_wallpapers_id"))
    private Set<DefaultWallpaper> defaultWallpapers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "categories_collection_wallpapers", joinColumns = @JoinColumn(name = "categories_id"), inverseJoinColumns = @JoinColumn(name = "collection_wallpapers_id"))
    private Set<CollectionWallpaper> collectionWallpapers = new HashSet<>();

    public Category(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DefaultWallpaper> getDefaultWallpapers() {
        return defaultWallpapers;
    }

    public void setDefaultWallpapers(Set<DefaultWallpaper> defaultWallpapers) {
        this.defaultWallpapers = defaultWallpapers;
    }

    public Set<CollectionWallpaper> getCollectionWallpapers() {
        return collectionWallpapers;
    }

    public void setCollectionWallpapers(Set<CollectionWallpaper> collectionWallpapers) {
        this.collectionWallpapers = collectionWallpapers;
    }

    @Override
    public boolean equals(Object obj) {
        Category category = (Category) obj;
        if(this.name.equals(category.getName()))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.name.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return id + " " + name;
    }
}
