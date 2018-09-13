package com.example.EazyPG.owner.Activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.EazyPG.owner.DetailsClasses.PG;
import com.example.ainesh.eazypg_owner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPGActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    FirebaseUser firebaseUser;

    TextInputEditText pgName;
    TextInputEditText bio;
    TextInputEditText gender;
    TextInputEditText landmark;
    TextInputEditText lastEntry;
    TextInputEditText location;
    TextInputEditText maxOccupancy;
    TextInputEditText bathroom;
    TextInputEditText room;
    TextInputEditText ownerName;
    TextInputEditText contact;
    TextInputEditText staffCount;
    TextInputEditText billDueDate;
    TextInputEditText rentDueDate;
    TextInputEditText electricityUnitCost;
    Snackbar snackbar;
    //TextView appliance;
    // TextView rooms;
    View view;

    FloatingActionButton saveButton;

    EditText input1,input2,input3,input4,input5,input6,input7,input8,input9,input10,input11,input12, input13, input14, input15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pg);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.rgb(250,250,250));
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.rgb(0,0,0));
            }
        }

        databaseReference = firebaseDatabase.getReference("PG/");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        pgName = findViewById(R.id.pgNameTextView);
        bio = findViewById(R.id.bioTextView);
        gender = findViewById(R.id.genderTextView);
        landmark = findViewById(R.id.landmarkTextView);
        lastEntry = findViewById(R.id.lastEntryTimeTextView);
        location = findViewById(R.id.locationTextView);
        maxOccupancy = findViewById(R.id.maxOccupancyTextView);
        bathroom = findViewById(R.id.noOfBathroomTextView);
        room = findViewById(R.id.noOfRoomTextView);
        ownerName = findViewById(R.id.ownerNameTextView);
        contact = findViewById(R.id.pgContactTextView);
        staffCount = findViewById(R.id.staffCountTextView);
        rentDueDate = findViewById(R.id.rentDueDateEditText);
        billDueDate = findViewById(R.id.billDueDateEditText);
        electricityUnitCost = findViewById(R.id.electricityUnitCostEditText);

        /*appliance = findViewById(R.id.applianceTextView);
        rooms = findViewById(R.id.roomTextView);*/

        saveButton = findViewById(R.id.saveButton);

        input1 = new EditText(this);
        input2 = new EditText(this);
        input3 = new EditText(this);
        input4 = new EditText(this);
        input5 = new EditText(this);
        input6 = new EditText(this);
        input7 = new EditText(this);
        input8 = new EditText(this);
        input9 = new EditText(this);
        input10 = new EditText(this);
        input11 = new EditText(this);
        input12 = new EditText(this);
        input13 = new EditText(this);
        input14 = new EditText(this);
        input15 = new EditText(this);
        view = findViewById(R.id.myPgLayout);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //databaseReference.keepSynced(true);
                String name1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("pgName").getValue(String.class);
                String bio1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("bio").getValue(String.class);
                String gender1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("gender").getValue(String.class);
                String landmark1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("landmark").getValue(String.class);
                String lastEntryTime1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("lastEntryTime").getValue(String.class);
                String location1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("location").getValue(String.class);
                String maxOccupancy1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("maxOccupancy").getValue(String.class);
                String bathroom1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("noOfBathroom").getValue(String.class);
                String room1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("noOfRooms").getValue(String.class);
                String ownername1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("ownername").getValue(String.class);
                String contact1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("pgContact").getValue(String.class);
                String staffCount1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("staffCount").getValue(String.class);
                String rentDueDate1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("rentDueDate").getValue(String.class);
                String billDueDate1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("billDueDate").getValue(String.class);
                String electricityUnitCost1 = dataSnapshot.child(firebaseUser.getUid()).child("PG Details").child("electricityUnitCost").getValue(String.class);

                pgName.setText(name1);
                bio.setText(bio1);
                gender.setText(gender1);
                landmark.setText(landmark1);
                lastEntry.setText(lastEntryTime1);
                location.setText(location1);
                maxOccupancy.setText(maxOccupancy1);
                bathroom.setText(bathroom1);
                room.setText(room1);
                ownerName.setText(ownername1);
                contact.setText(contact1);
                staffCount.setText(staffCount1);
                electricityUnitCost.setText(electricityUnitCost1);
                rentDueDate.setText(rentDueDate1);
                billDueDate.setText(billDueDate1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyPGActivity.this, "Failed to update!", Toast.LENGTH_SHORT).show();
            }
        });

      /*  rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyPGActivity.this, RoomsActivity.class));
            }
        });*/

        pgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input1.getParent()!=null) {
                    ((ViewGroup) input1.getParent()).removeView(input1);
                }
                input1.setText(pgName.getText().toString().trim());
                input1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });
                input1.setInputType(InputType.TYPE_CLASS_TEXT);
                input1.setSelection(input1.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter PG Name: ");

                builder.setView(input1);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        pgName.setText(input1.getText().toString());
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });

                builder.create().show();

            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input2.getParent()!=null) {
                    ((ViewGroup) input2.getParent()).removeView(input2);
                }
                input2.setText(bio.getText().toString().trim());
                input2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(80) });
                input2.setInputType(InputType.TYPE_CLASS_TEXT);
                input2.setSelection(input2.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Bio: ");

                builder.setView(input2);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        bio.setText(input2.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();

            }
        });

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input3.getParent()!=null) {
                    ((ViewGroup) input3.getParent()).removeView(input3);
                }
                input3.setText(gender.getText().toString().trim());
                input3.setFilters(new InputFilter[] { new InputFilter.LengthFilter(6) });
                input3.setInputType(InputType.TYPE_CLASS_TEXT);
                input3.setSelection(input3.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Gender: ");

                builder.setView(input3);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        gender.setText(input3.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        landmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input4.getParent()!=null) {
                    ((ViewGroup) input4.getParent()).removeView(input4);
                }
                input4.setText(landmark.getText().toString().trim());
                input4.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });
                input4.setInputType(InputType.TYPE_CLASS_TEXT);
                input4.setSelection(input4.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Landmark: ");

                builder.setView(input4);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        landmark.setText(input4.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        lastEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input5.getParent()!=null) {
                    ((ViewGroup) input5.getParent()).removeView(input5);
                }
                input5.setText(lastEntry.getText().toString().trim());
                input5.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
                input5.setInputType(InputType.TYPE_CLASS_DATETIME);
                input5.setSelection(input5.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Last Entry: ");

                builder.setView(input5);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        lastEntry.setText(input5.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input6.getParent()!=null) {
                    ((ViewGroup) input6.getParent()).removeView(input6);
                }
                input6.setText(location.getText().toString().trim());
                input6.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });
                input6.setInputType(InputType.TYPE_CLASS_TEXT);
                input6.setSelection(input6.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Location: ");

                builder.setView(input6);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        location.setText(input6.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        maxOccupancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input7.getParent()!=null) {
                    ((ViewGroup) input7.getParent()).removeView(input7);
                }
                input7.setText(maxOccupancy.getText().toString().trim());
                input7.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
                input7.setInputType(InputType.TYPE_CLASS_NUMBER);
                input7.setSelection(input7.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Max Occupancy: ");

                builder.setView(input7);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        maxOccupancy.setText(input7.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input8.getParent()!=null) {
                    ((ViewGroup) input8.getParent()).removeView(input8);
                }
                input8.setText(bathroom.getText().toString().trim());
                input8.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
                input8.setInputType(InputType.TYPE_CLASS_NUMBER);
                input8.setSelection(input8.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Number of Bathrooms: ");

                builder.setView(input8);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        bathroom.setText(input8.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input9.setText(room.getText().toString().trim());
                input9.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
                input9.setInputType(InputType.TYPE_CLASS_NUMBER);
                input9.setSelection(input9.getText().length());
                if(input9.getParent()!=null) {
                    ((ViewGroup) input9.getParent()).removeView(input9);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Number of Rooms: ");

                builder.setView(input9);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        room.setText(input9.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        ownerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input10.getParent()!=null) {
                    ((ViewGroup) input10.getParent()).removeView(input10);
                }
                input10.setText(ownerName.getText().toString().trim());
                input10.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });
                input10.setInputType(InputType.TYPE_CLASS_TEXT);
                input10.setSelection(input10.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Owner's Name: ");

                builder.setView(input10);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ownerName.setText(input10.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input11.getParent()!=null) {
                    ((ViewGroup) input11.getParent()).removeView(input11);
                }
                input11.setText(contact.getText().toString().trim());
                input11.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });
                input11.setInputType(InputType.TYPE_CLASS_PHONE);
                input11.setSelection(input11.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Contact Details: ");

                builder.setView(input11);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        contact.setText(input11.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });
        staffCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input12.getParent()!=null) {
                    ((ViewGroup) input12.getParent()).removeView(input12);
                }
                input12.setText(staffCount.getText().toString().trim());
                input12.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                input12.setInputType(InputType.TYPE_CLASS_NUMBER);
                input12.setSelection(input12.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Staff Count: ");

                builder.setView(input12);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        staffCount.setText(input12.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        rentDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input13.getParent()!=null) {
                    ((ViewGroup) input13.getParent()).removeView(input13);
                }
                input13.setText(rentDueDate.getText().toString().trim());
                input13.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                input13.setInputType(InputType.TYPE_CLASS_NUMBER);
                input13.setSelection(input13.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Rent Due Date Of Every Month: ");

                builder.setView(input13);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        rentDueDate.setText(input13.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        billDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input14.getParent()!=null) {
                    ((ViewGroup) input14.getParent()).removeView(input14);
                }
                input14.setText(rentDueDate.getText().toString().trim());
                input14.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                input14.setInputType(InputType.TYPE_CLASS_NUMBER);
                input14.setSelection(input14.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Bill Due Date Of Every Month: ");

                builder.setView(input14);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        rentDueDate.setText(input14.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        electricityUnitCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input15.getParent()!=null) {
                    ((ViewGroup) input15.getParent()).removeView(input15);
                }
                input15.setText(rentDueDate.getText().toString().trim());
                input15.setFilters(new InputFilter[] { new InputFilter.LengthFilter(2) });
                input15.setInputType(InputType.TYPE_CLASS_NUMBER);
                input15.setSelection(input15.getText().length());
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPGActivity.this);
                builder.setTitle("Your PG Details")
                        .setIcon(R.drawable.ic_edit_black_24dp)
                        .setMessage("Enter Cost Of Electricity Per Unit: ");

                builder.setView(input15);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        rentDueDate.setText(input15.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

       /* appliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MyPGActivity.this, ApplianceActivity.class));

            }
        });*/

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingPg();
                saveButton.setVisibility(View.VISIBLE);
            }
        });

        saveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MyPGActivity.this, "Save", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void addingPg() {

        String pgNameString = pgName.getText().toString().trim();
        String bioString = bio.getText().toString().trim();
        String locationString = location.getText().toString().trim();
        String ownerNameString = ownerName.getText().toString().trim();
        String contactString = contact.getText().toString().trim();
        String landmarkString = landmark.getText().toString().trim();
        String lastEntryString = lastEntry.getText().toString().trim();
        String genderString = gender.getText().toString().trim();
        String maxOccupancyString = maxOccupancy.getText().toString().trim();
        String staffCountString = staffCount.getText().toString().trim();
        String roomString = room.getText().toString().trim();
        String bathroomString = bathroom.getText().toString().trim();
        String rentDueDateString = rentDueDate.getText().toString().trim();
        String billDueDateString = billDueDate.getText().toString().trim();
        String electricityUnitCostString = electricityUnitCost.getText().toString().trim();

        //Getting current user information
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "PG/"+user.getUid();

        //adding database info for the current user
        databaseReference = FirebaseDatabase.getInstance().getReference(uid);

        final ProgressDialog progressDialog = ProgressDialog.show(MyPGActivity.this,"","Saving..",true);

        PG pg = new PG(pgNameString, bioString, locationString, ownerNameString, contactString, landmarkString, lastEntryString, genderString, maxOccupancyString, staffCountString, roomString, bathroomString, rentDueDateString, billDueDateString, electricityUnitCostString);
        databaseReference.child("PG Details").setValue(pg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss();
                snackbar = Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(MyPGActivity.this, R.color.DarkGreen));
                snackbar.show();
                saveButton.hide();
                saveButton.postDelayed(new Runnable() {
                    public void run() {
                        saveButton.show();
                    }
                }, 1800);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                snackbar = Snackbar.make(view, "Failed ! Unable to Save", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(ContextCompat.getColor(MyPGActivity.this, R.color.red));
                snackbar.show();
                saveButton.hide();
                saveButton.postDelayed(new Runnable() {
                    public void run() {
                        saveButton.show();
                    }
                }, 1800);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //     AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        new AlertDialog.Builder(MyPGActivity.this).setTitle("Save Details")
                .setMessage("Do you want to save details before going to home page?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addingPg();
                        startActivity(new Intent(MyPGActivity.this, HomePageActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MyPGActivity.this, HomePageActivity.class));
                        finish();
                    }
                })
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show();
    }

}