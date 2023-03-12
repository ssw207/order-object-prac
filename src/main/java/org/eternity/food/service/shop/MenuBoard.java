package org.eternity.food.service.shop;

import lombok.Data;
import org.eternity.food.domain.generic.money.Money;
import org.eternity.food.domain.shop.Menu;
import org.eternity.food.domain.shop.Shop;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MenuBoard {
    private Long shopId;
    private String shopName;
    private boolean open;
    private Money minOrderAmount;
    private List<MenuItem> menuItems;

    public MenuBoard(Shop shop) {
        this.shopId = shop.getId();
        this.shopName = shop.getName();
        this.open = shop.isOpen();
        this.minOrderAmount = shop.getMinOrderAmount();
        this.menuItems = toMenuItems(shop.getMenus());
    }

    private List<MenuItem> toMenuItems(List<Menu> menus) {
        return menus.stream().map(MenuItem::new).collect(Collectors.toList());
    }

    @Data
    public static class MenuItem {
        private Long menuId;
        private String menuName;
        private Money menuBasePrice;
        private String menuDescription;

        public MenuItem(Menu menu) {
            this.menuId = menu.getId();
            this.menuName = menu.getName();
            this.menuBasePrice = menu.getBasePrice();
            this.menuDescription = menu.getDescription();
        }
    }
}
