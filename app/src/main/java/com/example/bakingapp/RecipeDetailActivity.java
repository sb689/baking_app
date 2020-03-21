package com.example.bakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.example.bakingapp.model.Ingredient;
import com.example.bakingapp.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity implements MasterListFragment.IngredientsClickedListener,
                MasterListFragment.StepClickedListenerForward{


   // private ActivityRecipeDetailBinding mDataBinding;
    private static final String TAG = RecipeDetailActivity.class.getName();
    public static final String BUNDLE_EXTRA_RECIPE_DETAIL = "bundle_extra_recipe_detail";
    public static final String BUNDLE_EXTRA_KEY = "extra_key";
    public static final String BUNDLE_EXTRA_INGREDIENT_VALUE = "ingredient";
    public static final String BUNDLE_EXTRA_STEP_VALUE = "step";


    private static Recipe mRecipe;

    public static Recipe getRecipe(){
        return mRecipe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, ":::::::::::::::: inside onCreate");
        if(savedInstanceState == null) {
            Log.d(TAG, ":::::::::::::::: inside onCreate, savedInstanceState is null");
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            mRecipe = bundle.getParcelable(MainActivity.BUNDLE_EXTRA);

        }else{
            Log.d(TAG, ":::::::::::::::: inside onCreate, savedInstanceState is not null ");
            mRecipe = savedInstanceState.getParcelable(BUNDLE_EXTRA_RECIPE_DETAIL);
        }
        setContentView(R.layout.activity_recipe_detail);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, ":::::::::::: inside onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        mRecipe =  savedInstanceState.getParcelable(BUNDLE_EXTRA_RECIPE_DETAIL);
    }

    @Override
    public void ingredientsClicked() {

       Log.d(TAG, "i:::::::::::::step click rcvd in ingredients");
       Intent intent = new Intent(this, StepDetailActivity.class);
       Bundle bundle = new Bundle();
       bundle.putParcelable(BUNDLE_EXTRA_RECIPE_DETAIL, mRecipe);
       bundle.putString(BUNDLE_EXTRA_KEY, BUNDLE_EXTRA_INGREDIENT_VALUE);
       intent.putExtras(bundle);

       startActivity(intent);

    }

    @Override
    public void stepClickedForward(int position) {
        Log.d(TAG, "i:::::::::::::step click rcvd at position " + position);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putParcelable(BUNDLE_EXTRA_RECIPE_DETAIL, mRecipe);
        Log.d(TAG, ":::::::::::::::  has included mRecipe in outState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
