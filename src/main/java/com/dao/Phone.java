package com.dao;

import com.dao.Identities.PhoneId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@IdClass(PhoneId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Phone {

    @Id
    @SequenceGenerator(name = "phone_id_seq", sequenceName = "phone_id_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "phone_id_seq")
    private Long pid;
    @Id
    private Long areaCode;

    private String number;

}
