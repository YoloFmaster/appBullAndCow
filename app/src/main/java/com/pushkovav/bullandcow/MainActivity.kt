package com.pushkovav.bullandcow


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pushkovav.bullandcow.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game()
    }

    var currentEditText = 0

    private fun uniqueNumber(number: List<String>): Boolean {
        //проверка числа на уникальные числа
        for(i in number.indices){
            for (j in number.indices){
                if(i == j) continue
                if (number[i] == number[j]) {
                    return false
                }
            }
        }
        return true
    }

    private fun computerCreateNumber(): List<String> {
        //компьютер создаёт число
        var unique: Boolean
        val computerNumber = mutableListOf<Int>()
        var computerNumberString = mutableListOf<String>()
        do{
            for(i in 0 .. 3) {
                computerNumber.add((0..9).random())
                computerNumberString += computerNumber[i].toString()
            }
            unique = uniqueNumber(computerNumberString)
            if (!unique){
                computerNumber.clear()
                computerNumberString.clear()
            }
        } while (!unique)
        return computerNumberString
    }

    private fun showWarning(){
        Toast.makeText(this,"В числе не должно быть две одинаковых цифры",Toast.LENGTH_LONG).show()
    }
    private fun userCreateNumber():List<String> {
        //пользователь создаёт число
        var peopleNumberString = mutableListOf<String>()
        val myEditText = findViewById<EditText>(R.id.txt0)


//        val nameID = "textId" + (currentEditText - 1).toString()
//        val id = resources.getIdentifier(nameID, "id", getPackageName())
//        print(id)
//
//        myEditText.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) {
//                    if (currentEditText != 0){
//                        myEditText.setOnKeyListener(object : View.OnKeyListener {
//                            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
//                                if (keyCode == KeyEvent.KEYCODE_DEL) {
//                                    val pastId = getResources().getIdentifier("textId" + (currentEditText - 1).toString(), "id", getPackageName())
//                                    val pastEditText = findViewById<EditText>(pastId)
//                                    pastEditText.text.clear()
//                                    pastEditText.requestFocus()
//                                    pastEditText.isFocusableInTouchMode = true
//                                    myEditText.isFocusableInTouchMode = false
//                                    currentEditText--
//                                }
//                                return true
//                            }
//                        })
//                    }
//                    if (start == 0 && after == 0) {
//                        currentEditText--
//                    }
//                    if (start == 0 && after == 1) {
//                        peopleNumberString += s
//                        val nextId = getResources().getIdentifier("textId" + (currentEditText + 1).toString(), "id", getPackageName())
//                        val pastEditText = findViewById<EditText>(nextId)
//                        pastEditText.isFocusableInTouchMode = true
//                        pastEditText.requestFocus()
//                        myEditText.isFocusableInTouchMode = false
//                        currentEditText++
//                    }
//                }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//                }
//
//            override fun afterTextChanged(s: Editable?) {
//
//                }
//            })

        myEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ){

        }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                peopleNumberString.add(s.toString())
                val nextEditText = findViewById<EditText>(R.id.txt1)
                nextEditText.isFocusableInTouchMode = true
                nextEditText.requestFocus()
                myEditText.isFocusableInTouchMode = false
                currentEditText++
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val myEditText1 = findViewById<EditText>(R.id.txt1)

        myEditText1.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    val pastEditText = findViewById<EditText>(R.id.txt0)
                    pastEditText.isFocusableInTouchMode = true
                    pastEditText.requestFocus()
                    pastEditText.text.clear()
                    myEditText1.isFocusableInTouchMode = false
                    currentEditText--
                }
                return true
            }
        })

        myEditText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {

            }


            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (before == 0 && start == 0) {
                    peopleNumberString.add(s.toString())
                    if (uniqueNumber(peopleNumberString)) {
                        val nextEditText = findViewById<EditText>(R.id.txt2)
                        nextEditText.isFocusableInTouchMode = true
                        nextEditText.requestFocus()
                        myEditText1.isFocusableInTouchMode = false
                        currentEditText++
                    }
                    else{
                        myEditText1.text.clear()
                        peopleNumberString.removeAt(currentEditText)
                        showWarning()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        val myEditText2 = findViewById<EditText>(R.id.txt2)

        myEditText2.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    val pastEditText = findViewById<EditText>(R.id.txt1)
                    myEditText2.isFocusableInTouchMode = false
                    pastEditText.text.clear()
                    pastEditText.requestFocus()
                    pastEditText.isFocusableInTouchMode = true
                    currentEditText--
                }
                return true
            }
        })

        myEditText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {

            }
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (before == 0 && start == 0) {
                    peopleNumberString.add(s.toString())
                    if (uniqueNumber(peopleNumberString)) {
                        val nextEditText = findViewById<EditText>(R.id.txt3)
                        nextEditText.isFocusableInTouchMode = true
                        nextEditText.requestFocus()
                        myEditText2.isFocusableInTouchMode = false
                        currentEditText++
                    }
                    else{
                        peopleNumberString.removeAt(currentEditText)
                        myEditText2.text.clear()
                        showWarning()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        val myEditText3 = findViewById<EditText>(R.id.txt3)

        myEditText3.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    val pastEditText = findViewById<EditText>(R.id.txt2)
                    myEditText3.isFocusableInTouchMode = false
                    pastEditText.text.clear()
                    pastEditText.requestFocus()
                    pastEditText.isFocusableInTouchMode = true
                    currentEditText--
                }
                return true
            }
        })
        myEditText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (before == 0 && start == 0) {
                    peopleNumberString.add(s.toString())
                    if (uniqueNumber(peopleNumberString)) {
                        currentEditText++
                    } else {
                        peopleNumberString.removeAt(currentEditText)
                        myEditText3.text.clear()
                        showWarning()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        return peopleNumberString
    }

    private fun game() {
        val computerNumber = computerCreateNumber()
        val userNumber = userCreateNumber()
        if(currentEditText == 4) {
            var countBull = 0
            var countCow = 0
            for (i in computerNumber.indices) {
                if (userNumber[i] == computerNumber[i]) countBull++
                else {
                    for (j in computerNumber.indices) {
                        if (userNumber[i] == computerNumber[j]) {
                            countCow++
                            break
                        }
                    }
                }
                if (countBull == 4) {
                    println("Поздравляю вы выйграли")
                    break
                }
                print("К сожелению вы проиграли. число заданное компьютером $computerNumber")
            }
        }
    }
}

