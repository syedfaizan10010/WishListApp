package eu.test.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.test.wishlistapp.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditWish(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
){
    val snakMessage= remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if(id != 0L){
        val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState =""
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.Update_wish)
    else stringResource(id = R.string.Add_wish)
    ) {navController.navigateUp()} //Basically jus go up on app UI - from where we come from
    }){
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(10.dp))
            
            WishTextField(label = "Title", value = viewModel.wishTitleState,
                    onValueChange = {
                        viewModel.onWishTitleChanged(it)
                    })
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(label = "Description", value = viewModel.wishDescriptionState,
                onValueChange = {
                    viewModel.onWishDescriptionChanged(it)
                })
            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    //TO DO Update our wish
                    if(id != 0L){
                        //Update wish
                        viewModel.updateWish(
                            Wish(
                                id= id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snakMessage.value ="Wish Updated"
                    }
                    else{ //TO do Add a wish
                        viewModel.addWish (
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snakMessage.value="Wish has been created"
                    }
                }else{
                    snakMessage.value="Enter fields to create a wish"
                }
                scope.launch{
                    //scaffoldState.snackbarHostState.showSnackbar(snakMessage.value)
                    navController.navigateUp()
                }}) {
                Text(text = if(id != 0L) stringResource(id = R.string.Update_wish)
                            else stringResource(id = R.string.Add_wish),
                    style = TextStyle(fontSize = 18.sp)


                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChange:(String) -> Unit
){ OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label, color = Color.Black) },
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    colors = TextFieldDefaults.outlinedTextFieldColors(
        // using predefined Color
//        textColor = Color.Black,
        // using our own colors in Res.Values.Color
        focusedBorderColor = colorResource(id = R.color.black),
        unfocusedBorderColor = colorResource(id = R.color.black),
        cursorColor = colorResource(id = R.color.black),
        focusedLabelColor = colorResource(id = R.color.black),
        unfocusedLabelColor = colorResource(id = R.color.black)
    )
    )

}

@Preview
@Composable
fun WishTestFieldPrev(){
    WishTextField(label = "text", value = "text", onValueChange = {})
}

