package com.sbbank.accounts.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity{


    @Column(name="customer_id")
    private Long customerId;

    @Id
    @Column(name="account_number")
    private Long accountNumber;

    private String accountType;

    private String branchAddress;


}
