package com.example.roomlab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_COURSE_REQUEST = 1;
    private RecyclerView coursesRV;
    private static final int EDIT_COURSE_REQUEST = 2;
    private ViewModal viewmodal;
    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView1 = findViewById(R.id.contentImage1);
        ImageView imageView2 = findViewById(R.id.contentImage2);
        ImageView imageView3 = findViewById(R.id.contentImage3);
        ImageView imageView4 = findViewById(R.id.contentImage4);

        String imageUrl1 = "https://jlaonline.co.za/wp-content/uploads/2020/09/140-Black-1-1.png";
        String imageUrl2 = "https://4.imimg.com/data4/LG/MJ/MY-13820743/ceramic-coffee-mug-250x250.jpg";
        String imageUrl3 = "https://clothform.com/wp-content/uploads/2019/10/GHOSTEMANE-Logo-Hoodie.jpg";
        String imageUrl4 = "https://rvb-img.reverb.com/image/upload/s--RciLWGlU--/t_card-square/v1591219067/aj5y3qsbmizrsc3d6n9u.jpg";

        try {
            Picasso.get().load(imageUrl1).into(imageView1);
            Picasso.get().load(imageUrl2).into(imageView2);
            Picasso.get().load(imageUrl3).into(imageView3);
            Picasso.get().load(imageUrl4).into(imageView4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        coursesRV = findViewById(R.id.idRVCourses);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewCourseActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        coursesRV.setHasFixedSize(true);

        final CourseRVAdapter adapter = new CourseRVAdapter();
        coursesRV.setAdapter(adapter);

        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        viewmodal.getAllCourses().observe(this, new Observer<List<CourseModal>>() {
            @Override
            public void onChanged(List<CourseModal> models) {
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Cart item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(coursesRV);

        adapter.setOnItemClickListener(new CourseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CourseModal model) {
                Intent intent = new Intent(MainActivity.this, NewCourseActivity.class);
                intent.putExtra(NewCourseActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewCourseActivity.EXTRA_COURSE_NAME, model.getCourseName());
                intent.putExtra(NewCourseActivity.EXTRA_DESCRIPTION, model.getCourseDescription());
                intent.putExtra(NewCourseActivity.EXTRA_DURATION, model.getCourseDuration());
                startActivityForResult(intent, EDIT_COURSE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK) {
            String courseName = data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            String courseDescription = data.getStringExtra(NewCourseActivity.EXTRA_DESCRIPTION);
            String courseDuration = data.getStringExtra(NewCourseActivity.EXTRA_DURATION);
            CourseModal model = new CourseModal(courseName, courseDescription, courseDuration);
            viewmodal.insert(model);
            Toast.makeText(this, "Cart saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewCourseActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Cart can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            String courseDesc = data.getStringExtra(NewCourseActivity.EXTRA_DESCRIPTION);
            String courseDuration = data.getStringExtra(NewCourseActivity.EXTRA_DURATION);
            CourseModal model = new CourseModal(courseName, courseDesc, courseDuration);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Cart updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cart not saved", Toast.LENGTH_SHORT).show();
        }

        List<CartItem> cartItems = null;
        CartAdapter cartAdapter = new CartAdapter(cartItems);
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CartItem cartItem) {
                addToCart(cartItem.getItemName(), cartItem.getItemDescription(), cartItem.getItemPrice());
                Toast.makeText(MainActivity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setAdapter(cartAdapter);
    }

    private void addToCart(String itemName, String itemDescription, String itemPrice) {
        CartViewModel cartViewModel = new CartViewModel(getApplication());
        cartViewModel.insert(new CartItem(itemName, itemDescription, itemPrice));
        Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
    }

    public void openGpsScreen(View view) {
        Intent intent = new Intent(this, GPS.class);
        startActivity(intent);
    }

    public void openFilterScreen(View view) {
        Intent intent = new Intent(this, PlaceholderActivity.class);
        startActivity(intent);
    }

    public void openCartScreen(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
