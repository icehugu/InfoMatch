package com.example.infomatch.ui.special;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.infomatch.databinding.FragmentSpecialBinding;
import java.util.Calendar;


public class SpecialFragment extends Fragment {

    private specialViewModel viewModel;
    private FragmentSpecialBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpecialBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(specialViewModel.class);

        binding.backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.selectAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        viewModel.setmHour(selectedHour);
                        viewModel.setmMinute(selectedMinute);
                        binding.selectedTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        binding.selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                viewModel.setmYear(year);
                                viewModel.setmMonth(monthOfYear);
                                viewModel.setmDay(dayOfMonth);
                                binding.selectedDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        binding.setDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getmDay()!=-1){

                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(viewModel.getmYear(), viewModel.getmMonth(), viewModel.getmDay());
                    Intent intent = new Intent(Intent.ACTION_INSERT)
                            .setData(CalendarContract.Events.CONTENT_URI)
                            .putExtra(CalendarContract.Events.TITLE, "play infomatch")
                            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                            .putExtra(CalendarContract.Events.ALL_DAY, true);
                    if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
                        startActivity(intent);
                }
            }
                else{
                    Toast.makeText(requireContext(), "please chose a date for the calendar", Toast.LENGTH_SHORT).show();
                }
        }});


        binding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getmHour()!=-1) {
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                            .putExtra(AlarmClock.EXTRA_MESSAGE, "play infomatch")
                            .putExtra(AlarmClock.EXTRA_HOUR, viewModel.getmHour())
                            .putExtra(AlarmClock.EXTRA_MINUTES, viewModel.getmMinute());
                    if (intent.resolveActivity(requireContext().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(requireContext(), "please chose a time for the alarm", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return binding.getRoot();
    }


}
