package com.wposs.apirickandmorty.View.DetailCharacter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import com.wposs.apirickandmorty.Base.App;
import com.wposs.apirickandmorty.Models.Character;
import com.wposs.apirickandmorty.R;
import com.wposs.apirickandmorty.Utils.Constants;
import com.wposs.apirickandmorty.Utils.DateFormatType;
import com.wposs.apirickandmorty.Utils.DateUtils;
import com.wposs.apirickandmorty.Utils.Extensions;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends App {
    private Character character;
    private CircleImageView image;
    private TextView name, status, species, type, gender, origin, location, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.iv_imageDetail);
        name = findViewById(R.id.tv_nameDetail);
        status = findViewById(R.id.tv_statusDetail);
        species = findViewById(R.id.tv_spesiesDetail);
        type = findViewById(R.id.tv_typeDetail);
        gender = findViewById(R.id.tv_genderDetail);
        origin = findViewById(R.id.tv_originDetail);
        location = findViewById(R.id.tv_locationDetail);
        date = findViewById(R.id.tv_dateDetail);

        // Obtener el Intent que inició la actividad
        Intent intent = getIntent();
        character = intent.getParcelableExtra(Constants.Key.CHARACTER);
        completeTransactionData();
    }
    private void completeTransactionData(){
        if (character != null){
            Extensions.convertImageService(character.getImage(), image, 300);
            name.setText(character.getName());
            status.setText(character.getStatus());
            species.setText(character.getSpecies());
            type.setText(character.getType());
            gender.setText(character.getGender());
            origin.setText(character.getOrigin().getName());
            location.setText(character.getLocation().getName());
            date.setText(DateUtils.formatDateString(character.getCreated(), DateFormatType.FORMAT_2));
        }
    }
}