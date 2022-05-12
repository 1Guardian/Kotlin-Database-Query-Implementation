package com.example.friendlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friendlist.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

class MainActivity : AppCompatActivity() {

    public val contextPassed = this;

    //setup main access
    private lateinit var mainEnv: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var popMoviesAdapter: RecyclerView.Adapter<PopMoviesAdapter.ViewHolder>? = null;
    private var upMoviesAdapter: RecyclerView.Adapter<UpMoviesAdapter.ViewHolder>? = null;
    private var popPeopleAdapter: RecyclerView.Adapter<PopPeopleAdapter.ViewHolder>? = null;

    //function to update the spinner
    fun<T> spinnerPop(input: List<Class<T>>){

    }


    //function to load the movies
    fun loadPopMovies(){

        //get the associated information from online for the population of the movie cards
        val request = PopMoviesServiceBuilder.buildService(PopMoviesInterface::class.java)
        val call = request.getMovies("b3bb440363cfdf76de1550568f883387")

        //make the request
        call.enqueue(object: Callback<PopMovies>{
            override fun onResponse(
                call: Call<PopMovies>,
                response: Response<PopMovies>
            ) {
                if (response.isSuccessful) {
                    mainEnv.recyclerView.apply {

                        //set the recyclerview adapter
                        adapter = PopMoviesAdapter(response.body()!!.results);

                        //get the spinner
                        var spinner: Spinner = mainEnv.spinner;

                        // Create an ArrayAdapter using the string array and a default spinner layout
                        var values: MutableList<String> = ArrayList()
                        for (element in response.body()!!.results) {
                            values.add(element.title);
                        }

                        //make arrayadapter
                        var arrayAdapter: ArrayAdapter<*>
                        arrayAdapter = ArrayAdapter(
                            contextPassed,
                            android.R.layout.simple_list_item_1, values
                        )

                        spinner.adapter = arrayAdapter;
                    }
                }
                else {
                    Toast.makeText(this@MainActivity, "Failed Retrieval of Movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PopMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        //set layout manager
        mainEnv.recyclerView.layoutManager = layoutManager;

        //set adapter
        mainEnv.recyclerView.adapter = popMoviesAdapter;
    }

    //function to load the people
    fun loadPopPeople(){
        //get the associated information from online for the population of the movie cards
        val request = PopPeopleServiceBuilder.buildService(PopPeopleInterface::class.java)
        val call = request.getPeople("b3bb440363cfdf76de1550568f883387")

        //make the request
        call.enqueue(object: Callback<PopPeople>{
            override fun onResponse(
                call: Call<PopPeople>,
                response: Response<PopPeople>
            ) {
                if (response.isSuccessful) {
                    mainEnv.recyclerView.apply {

                        //set recyclerview adapter
                        adapter = PopPeopleAdapter(response.body()!!.results);

                        //get the spinner
                        var spinner: Spinner = mainEnv.spinner;

                        // Create an ArrayAdapter using the string array and a default spinner layout
                        var values: MutableList<String> = ArrayList()
                        for (element in response.body()!!.results) {
                            values.add(element.name);
                        }

                        //make arrayadapter
                        var arrayAdapter: ArrayAdapter<*>
                        arrayAdapter = ArrayAdapter(
                            contextPassed,
                            android.R.layout.simple_list_item_1, values
                        )

                        spinner.adapter = arrayAdapter;
                    }
                }
                else {
                    Toast.makeText(this@MainActivity, "Failed Retrieval of People", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PopPeople>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        //set layout manager
        mainEnv.recyclerView.layoutManager = layoutManager;

        //set adapter
        mainEnv.recyclerView.adapter = popPeopleAdapter;
    }

    //function to load the upcoming movies
    fun loadUpMovies(){
        //get the associated information from online for the population of the movie cards
        val request = UpMovieServiceBuilder.buildService(UpMovieInterface::class.java)
        val call = request.getUpMovie("b3bb440363cfdf76de1550568f883387")

        //make the request
        call.enqueue(object: Callback<UpMovies>{
            override fun onResponse(
                call: Call<UpMovies>,
                response: Response<UpMovies>
            ) {
                if (response.isSuccessful) {
                    mainEnv.recyclerView.apply {

                        //set recyclerview adapter
                        adapter = UpMoviesAdapter(response.body()!!.results);

                        //get the spinner
                        var spinner: Spinner = mainEnv.spinner;

                        // Create an ArrayAdapter using the string array and a default spinner layout
                        var values: MutableList<String> = ArrayList()
                        for (element in response.body()!!.results) {
                            values.add(element.title);
                        }

                        //make arrayadapter
                        var arrayAdapter: ArrayAdapter<*>
                        arrayAdapter = ArrayAdapter(
                            contextPassed,
                            android.R.layout.simple_list_item_1, values
                        )

                        spinner.adapter = arrayAdapter;
                    }
                }
                else {
                    Toast.makeText(this@MainActivity, "Failed Retrieval of Upcoming Movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UpMovies>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        //set layout manager
        mainEnv.recyclerView.layoutManager = layoutManager;

        //set adapter
        mainEnv.recyclerView.adapter = upMoviesAdapter;
    }

    //same main code from previous project
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //bind
        mainEnv = ActivityMainBinding.inflate(layoutInflater);

        //set env
        setContentView(mainEnv.root);

        //remove the title bar
        this.supportActionBar!!.hide();

        //make the recyclerview
        layoutManager = LinearLayoutManager(applicationContext);

        //set fixedsize for recycler
        mainEnv.recyclerView.setHasFixedSize(true);

        //setup the bottom-bar click controls
        mainEnv.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.popMovies -> loadPopMovies();
                R.id.popPeople -> loadPopPeople();
                R.id.upMovies -> loadUpMovies();
            }
            true
        }

        //setup listener for the spinner
        mainEnv.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //make recyclerview go to index on click
                mainEnv.recyclerView.scrollToPosition(position);
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        //call the first tab just to load something
        loadPopMovies();
    }
}