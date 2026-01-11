package org.example.txcommon.balance;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceUpdateRequestDto {

    private String tid;

    private String fromAccountId;

    private String toAccountId;

    private Long amount;

}
