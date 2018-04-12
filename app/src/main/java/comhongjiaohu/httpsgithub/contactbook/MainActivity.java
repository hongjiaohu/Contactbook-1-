/**
 * Name:Hongjiao Hu
 * Course:CS40S
 * Teacher:Mr.Hardman
 * Lab #3,Program# 1
 * Date Last Modified: 4/6/18
 */
package comhongjiaohu.httpsgithub.contactbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Contact[] contactsArray;
    private static int numContactsAdded;
    private EditText mNameInput;
    private EditText mPhoneInput;
    private EditText mEmailInput;
    private TextView mErrorMessage;
    private TextView mSortedList;

    String name;
    String phone;
    String e_mail;

    private int insertionSteps = 0;
    private int selectionSteps = 0;
    private int quickSteps = 0;

    @Override
    /**
     * onCreate is the method that is run when we create an instance of this activity
     *
     * @param savedInstanceState is a Bundle object that allows the Activity to restore
     *                           itself to a previously saved instance
     * @return Nothing is returned
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameInput = (EditText) findViewById(R.id.et_name);
        mPhoneInput = (EditText) findViewById(R.id.et_phone);
        mEmailInput = (EditText) findViewById(R.id.et_e_mail);
        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mSortedList = (TextView) findViewById(R.id.tv_sorted_lists);

        contactsArray = new Contact[50];
        numContactsAdded = 0;

    }

    /**
     * addContact adds a new contact to the array of contacts we will need to sort
     *
     * @param vw is the View that is related to this method
     * @return Nothing is returned
     */
    private void addTheContact(View vw)
    {
        InputMethodManager inputManager = (InputMethodManager ) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Contact userInput;

        if( inputManager != null )
        {
            inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(), 0);
        }

        if (numContactsAdded <= 0)
        {
            mErrorMessage.setText("You must enter at least a name to add a contact");
        }
        if( numContactsAdded >= 50 )
        {
            mErrorMessage.setText( "You have added the maximum amount of contacts." );

            if( inputManager != null )
            {
                inputManager.hideSoftInputFromWindow(vw.getApplicationWindowToken(), 0);
            }
        }
        else
        {
            numContactsAdded++;

            mErrorMessage.setText( "Contact added successfully" );
        }

        String nameInput;
        String phoneInput;
        String emailInput;

        numContactsAdded++;
    }

    /**
     * sortContacts sorts the contacts that the user entered and displays them in order of their names
     *
     * @param vw is the View that is related to this method
     * @return Nothing is returned
     */
    private void sortContacts(View vw)
    {
        String storeList = "";

        //insertionSteps();
        //selectionSteps();
        //quickSteps();

        mErrorMessage.setText("");
        mSortedList.setText(storeList);
    }

    /**
     * insertionSort uses the insetion Sort algorithm to sort a list of thems in ascending order
     *
     * @param "" There are no parameters
     * @return a String that displays the sorted list and how many steps it took
     */
    private String insertionSort()
    {
        Contact key;
        int index;

        String result;

        for ( int j = 1; j < numContactsAdded; j++ )
        {
            key = contactsArray[j];
            index = j-1;

            while( j >= 0 && ( contactsArray[j].toString() ).compareTo(key.toString()) > 0 )
            {
                contactsArray[j+1] = contactsArray[j];
                j = j - 1;
            }
            contactsArray[j+1] = key;
        }
        result = "insertion Sort Result:\n";

        for ( int k = 0; k < contactsArray.length; k++)
        {
            result += contactsArray[k] + ", \t";
        }

        result += "\nThis took " + insertionSteps + " steps to complete.\n\n";

        return result;
    }

    /**
     * selectionSort uses the Selection Sort algorithm  to Sort a list of thems in ascending order
     *
     * @param "" There are no parameters
     * @return a String that displays the sorted list and how many steps it took
     */
    private String selectionSort()
    {
        int minIndex;
        Contact toSwap;

        String result;

        for(int j = 0; j < numContactsAdded - 1; j++ )
        {
            minIndex = j;

            for( int k = j+1; k < numContactsAdded; k++ )
            {
                if( contactsArray[k].toString().compareTo(contactsArray[minIndex].toString()) < 0)
                {
                    minIndex = k;
                }
            }

            toSwap = contactsArray[minIndex];
            contactsArray[minIndex] = contactsArray[j];
            contactsArray[j] = toSwap;

        }

        result = "Selection Sort Result:\n";
        for(int m = 0; m < contactsArray.length; m++)
        {
            result += contactsArray[m] + ",\n";
        }

        result += "\nThis took " + selectionSteps + " steps to complete.\n\n";

        return result;
    }

    /**
     * quickSort uses the Quick Sort algorithm  to Sort a list of thems in ascending order
     *
     * @param contactsArray is the array we are sorting
     * @param low is the beginning index of the section of the away we would like to sort
     * @param high is the ending index of the section of the away we would like to sort
     * @return Nothing is returned
     */
    private void quickSort(Contact[] contactsArray, int low, int high )
    {
        int middle;
        int i;
        int j;

        Contact pivot;
        Contact toSwap;

        if( low < high )
        {
            middle = low + (high - low) /2;
            pivot = contactsArray[middle];

            i = low;
            j = high;

            while (i <= j)
            {
                while( (contactsArray[i].toString() ).compareTo(pivot.toString()) < 0 )
                {
                    i++;
                }

                while( (contactsArray[i].toString() ).compareTo(pivot.toString()) > 0 )
                {
                    j--;
                }

                if( i <= j )
                {
                    toSwap = contactsArray[i];
                    contactsArray[i] = contactsArray[j];
                    contactsArray[j] = toSwap;
                    i++;
                    j++;
                }
            }

            if (low < j )
            {
                quickSort( contactsArray, low, j );
            }

            if( high > i )
            {
                quickSort(contactsArray, i, high );
            }
        }
    }
}
