package org.eternity.food.domain.shop;

import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.generic.money.Ratio;
import org.junit.Test;

import static org.eternity.food.domain.Fixtures.aShop;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShopTest {
    @Test
    public void 최소주문금액_체크() {
        Shop shop = aShop().minOrderAmount(Money.wons(15000)).build();

        assertThat(shop.isValidOrderAmount(Money.wons(14000)), is(false));
        assertThat(shop.isValidOrderAmount(Money.wons(15000)), is(true));
        assertThat(shop.isValidOrderAmount(Money.wons(16000)), is(true));
    }

    @Test
    public void 수수료_부과() {
        Shop shop = aShop()
                        .commissionRate(Ratio.valueOf(0.1))
                        .commission(Money.ZERO)
                        .build();

        shop.billCommissionFee(Money.wons(1000));

        assertThat(shop.getCommission(), is(Money.wons(100)));
    }
}
