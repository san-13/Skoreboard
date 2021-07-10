package com.sv.skoreboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sv.skoreboard.databinding.ActivityScoreboardBinding

class Scoreboard : AppCompatActivity() {
    lateinit var binding: ActivityScoreboardBinding
    var run =0
    var balls=0
    var cover=0
    var cwicket=0
    var overs=0
    var wickets=0
    var crr:Double=0.00
    var bteam="Bteam"
    var team="ateam"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val oversinstring = intent?.extras?.getString("overs").toString()
        overs=oversinstring.toInt()
        val wicketinstring = intent?.extras?.getString("wickets").toString()
        wickets=wicketinstring.toInt()
        team=intent?.extras?.getString("teamname").toString()
        bteam=intent?.extras?.getString("bteamname").toString()
        binding.scoretab.text="$run | $cwicket"
        binding.overtext.text="$cover.$balls of $overs"
        binding.Teamname.text=team
        binding.runrate.text="$crr"
    }
    fun score(x:Int){
        if(cwicket==wickets){                           //Check All out
           // Toast.makeText(this,"All out!",Toast.LENGTH_SHORT).show()
        }
        else if(cover==overs){                          //Check Overs
            //Toast.makeText(this,"Innings Over",Toast.LENGTH_SHORT).show()
        }
        else if(binding.wckt.isChecked && binding.wide.isChecked) {      //wide+wicket
            run += x + 1
            cwicket += 1
        }
        else if(binding.wckt.isChecked) //Wicket
        {
            if(binding.noball.isChecked || binding.wide.isChecked){ //wicket +noball|wide
                run+=x+1
                cwicket+=1
            }
            else {
                run += x
                cwicket += 1
                if (balls == 5) {
                    cover += 1
                    balls = 0
                } else {
                    balls += 1
                }
            }
        }
        else if(binding.wide.isChecked) //Wide
        {   run += x+1
        }
        else if(binding.noball.isChecked) //No Ball
        {
            run+= x+1
        }

        else if(binding.bye.isChecked){ //bye
            run+=x
            if(balls==5)
            {cover+=1
                balls=0}
            else{balls+=1}
        }
        else{                           //normal
            run+=x
            if(balls==5)
            {cover+=1
                balls=0}
            else{balls+=1}
        }
        crr=(run.toDouble()/(cover.toDouble()+(balls.toDouble()/6)))
        binding.scoretab.text="$run | $cwicket"
        binding.overtext.text="$cover.$balls of $overs"
        binding.runrate.text="$crr"
        binding.wide.isChecked=false
        binding.noball.isChecked=false
        binding.bye.isChecked=false
        binding.wckt.isChecked=false
    }
    fun four(view: View) {
        score(4)
    }

    fun two(view: View) {
        score(2)
    }
    fun one(view: View) {
        score(1)
    }
    fun zero(view: View) {
        score(0)
    }
    fun six(view: View) {
        score(6)
    }
    fun three(view: View) {
        score(3)
    }
    fun five(view: View) {
        score(5)
    }

    fun cbwide(view: View) {
        if (binding.noball.isChecked || binding.bye.isChecked) {
            binding.wide.isChecked = false
        }
    }

    fun cbnoball(view: View) {
        if(binding.wide.isChecked){
            binding.noball.isChecked=false
        }
    }

    fun cbbye(view: View) {
        if(binding.wide.isChecked){
            binding.bye.isChecked=false
        }
    }

    fun next(view: View) {
        if(cover==overs || cwicket==wickets || binding.isdeclare.isChecked){
            val intent =Intent(this,Skoreboardc::class.java)
            intent.putExtra("total",run)
            intent.putExtra("overs",overs)
            intent.putExtra("wickets",wickets)
            intent.putExtra("teamname",bteam)
            intent.putExtra("bteamname",team)
            startActivity(intent)
        }
    }

}