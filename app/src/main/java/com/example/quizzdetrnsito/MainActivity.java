package com.example.quizzdetrnsito;

import static com.example.quizzdetrnsito.QuestionAnswer.choices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestaoTextView;
    TextView QuestaoTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    ImageView imgPlaca;

    public static int images [] = {
            R.drawable.sentidoproibido,
            R.drawable.preferencia,
            R.drawable.proibidoestacionar,
            R.drawable.cruzamento,
            R.drawable.lombada,

    };

    int score = 0;
    int totalquestao = QuestionAnswer.question.length;
    int currentQuestion = 0;
    String selectedAnswer = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestaoTextView = findViewById(R.id.totalQuestao);
        imgPlaca = findViewById(R.id.imgPlaca);
        QuestaoTextView = findViewById(R.id.Questao);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submitBtn = findViewById(R.id.submitBtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestaoTextView.setText("Número total de questões: " +totalquestao);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.RED);
        ansB.setBackgroundColor(Color.RED);
        ansC.setBackgroundColor(Color.RED);
        ansD.setBackgroundColor(Color.RED);

        Button clickedButton = (Button) view;
        if(clickedButton.getId() == R.id.submitBtn){
            if(selectedAnswer.equals("")){
                return;
            }
                if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestion])){
                    score++;
                }
            currentQuestion++;
            loadNewQuestion();
            selectedAnswer = "";

        }else{
           selectedAnswer = clickedButton.getText().toString();
           clickedButton.setBackgroundColor(Color.BLACK);

           submitBtn.setEnabled(true);
        }


    }

    void loadNewQuestion(){

        if(currentQuestion == totalquestao){
            finishQuiz();
            return;
        }

        QuestaoTextView.setText(QuestionAnswer.question[currentQuestion]);
        ansA.setText(choices[currentQuestion][0]);
        ansB.setText(choices[currentQuestion][1]);
        ansC.setText(choices[currentQuestion][2]);
        ansD.setText(choices[currentQuestion][3]);


        imgPlaca.setImageResource(images[currentQuestion]);
        ansA.setText(choices[currentQuestion][0]);
        ansB.setText(choices[currentQuestion][1]);
        ansC.setText(choices[currentQuestion][2]);
        ansD.setText(choices[currentQuestion][3]);

    }


    void finishQuiz() {
        String passStatus = "";
        if (score > totalquestao - 2) {
            passStatus = "Aprovado(a)!";

        } else {
            passStatus = "Que pena! Tente outra vez";
        }


        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Sua pontuação é " + score + " de " + totalquestao)
                .setPositiveButton("Jogar novamente", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        score = 0;
        currentQuestion = 0;
        loadNewQuestion();
    }
}
