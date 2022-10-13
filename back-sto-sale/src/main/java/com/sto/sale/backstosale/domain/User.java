package com.sto.sale.backstosale.domain;

import com.sto.sale.backstosale.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    private Long user_id;
    private Integer user_account;
    // use purun schema
    @Column(name = "user_name")
    private String user_nm;

    private Integer user_stat;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Transaction> transactions;

    public User(Long user_id) {
        this.user_id = user_id;
    }

    public User(UserDto userDto) {
        this.user_id = userDto.getUser_id();
        this.user_account = userDto.getUser_account();
        this.user_nm = userDto.getUser_nm();
        this.user_stat = userDto.getUser_stat();
    }
}
