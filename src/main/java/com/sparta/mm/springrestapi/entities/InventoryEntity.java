package com.sparta.mm.springrestapi.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "inventory", schema = "sakila")
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Basic
    @Column(name = "film_id", insertable = false, updatable = false)
    private Integer filmId;
    @Basic
    @Column(name = "store_id", insertable = false, updatable = false)
    private Integer storeId;
    @Basic
    @Column(name = "last_update")
    private LocalDate lastUpdate;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
    private FilmEntity filmByFilmId;
    @OneToMany(mappedBy = "inventoryByInventoryId")
    private Collection<RentalEntity> rentalsByInventoryId;
    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id", nullable = false)
    private StoreEntity storeByStoreId;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEntity that = (InventoryEntity) o;
        return Objects.equals(inventoryId, that.inventoryId) && Objects.equals(filmId, that.filmId) && Objects.equals(storeId, that.storeId) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, filmId, storeId, lastUpdate);
    }

    public FilmEntity getFilmByFilmId() {
        return filmByFilmId;
    }

    public void setFilmByFilmId(FilmEntity filmByFilmId) {
        this.filmByFilmId = filmByFilmId;
    }

    public Collection<RentalEntity> getRentalsByInventoryId() {
        return rentalsByInventoryId;
    }

    public void setRentalsByInventoryId(Collection<RentalEntity> rentalsByInventoryId) {
        this.rentalsByInventoryId = rentalsByInventoryId;
    }

    public StoreEntity getStoreByStoreId() {
        return storeByStoreId;
    }

    public void setStoreByStoreId(StoreEntity storeByStoreId) {
        this.storeByStoreId = storeByStoreId;
    }
}
