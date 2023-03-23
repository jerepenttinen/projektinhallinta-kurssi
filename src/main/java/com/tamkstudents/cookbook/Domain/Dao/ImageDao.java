package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="image") @Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_seq")
    @SequenceGenerator(name = "image_seq", sequenceName = "image_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "image_data")
    private byte[] image;
}
