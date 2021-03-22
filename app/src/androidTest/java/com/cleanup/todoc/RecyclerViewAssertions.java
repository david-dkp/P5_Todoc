package com.cleanup.todoc;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class RecyclerViewAssertions {

    public static ItemCountAssertion withItemCount(int count) {
        return new ItemCountAssertion(count);
    }

    private static class ItemCountAssertion implements ViewAssertion {

        private int count;

        public ItemCountAssertion(int count) {
            this.count = count;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) throw noViewFoundException;

            RecyclerView recyclerView = (RecyclerView) view;

            assert recyclerView.getAdapter().getItemCount() == this.count;
        }
    }
}
