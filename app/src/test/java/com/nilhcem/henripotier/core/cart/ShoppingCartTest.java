package com.nilhcem.henripotier.core.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ShoppingCartTest {

    private ShoppingCart cart;

    @Before
    public void setup() {
        cart = new ShoppingCart(RuntimeEnvironment.application);
    }

    @Test
    public void should_return_false_when_not_in_cart() {
        // Given
        cart.removeFromCart("OMG");

        // When
        boolean result = cart.isInCart("OMG");

        // Then
        assertThat(result).isFalse();
    }

    @Test
    @SuppressLint("CommitPrefEdits")
    public void should_return_true_when_in_cart() {
        // Given
        getSharedPreferences().edit().putBoolean("OMG", true).commit();

        // When
        boolean result = cart.isInCart("OMG");

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void should_add_to_cart() {
        // Given
        String isbn = "ISBN_ADDED";

        // When
        cart.addToCart(isbn);

        // Then
        assertThat(getSharedPreferences().getBoolean(isbn, false)).isTrue();
    }

    @Test
    @SuppressLint("CommitPrefEdits")
    public void should_remove_from_cart() {
        // Given
        String isbn = "ISBN_REMOVED";
        SharedPreferences sharedPreferences = getSharedPreferences();
        sharedPreferences.edit().putBoolean(isbn, true).commit();

        // When
        cart.removeFromCart(isbn);

        // Then
        assertThat(getSharedPreferences().getBoolean(isbn, false)).isFalse();
    }

    @Test
    public void should_return_empty_list_when_no_isbn_in_cart() {
        // When
        List<String> isbns = cart.getAllIsbnsInCart();

        // Then
        assertThat(isbns).isEmpty();
    }

    @Test
    public void should_return_list_of_all_isbns_in_cart() {
        // Given
        cart.addToCart("ISBN1");
        cart.addToCart("ISBN3");
        cart.addToCart("ISBN1");
        cart.addToCart("ISBN2");

        // When
        List<String> isbns = cart.getAllIsbnsInCart();

        // Then
        assertThat(cart.getNbItemsInCart()).isEqualTo(3);
        assertThat(isbns).hasSize(3).contains("ISBN1", "ISBN2", "ISBN3");
        assertThat(cart.isInCart("ISBN1")).isTrue();
    }

    private SharedPreferences getSharedPreferences() {
        return RuntimeEnvironment.application.getSharedPreferences(ShoppingCart.prefsName, Context.MODE_PRIVATE);
    }
}
