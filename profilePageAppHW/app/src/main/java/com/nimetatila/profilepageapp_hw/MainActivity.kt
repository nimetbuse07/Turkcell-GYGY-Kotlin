package com.nimetatila.profilepageapp_hw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nimetatila.profilepageapp_hw.ui.theme.Pink40
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfilePageScreen()
        }
    }
}
@Composable
fun ProfilePageScreen() {

    val skills = listOf(
        "Kotlin", "Swift", "Firebase", "Python",
        "Data Science", "English", "AI"
    )

    var selectedSkill by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            ProfileHeaderSection()
        }

        items(skills) { skill ->
            SkillItem(
                skill = skill,
                isSelected = skill == selectedSkill,
                onClick = { selectedSkill = skill }
            )
        }
        
        item {
            Spacer(modifier = Modifier.height(16.dp))
            ContactMe(
                email = "nimetbuse@gmail.com",
                linkedin = "linkedin.com/in/nimet"
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
@Composable
fun ProfileHeaderSection() {

    Box(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .background(
                    color = Pink40,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(130.dp)
                    .shadow(
                        elevation = 20.dp,
                        shape = CircleShape,
                        clip = false
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Nimet Atila",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("Kocaeli Üniversitesi", fontSize = 14.sp, color = Color.DarkGray)
            Text("Bilgisayar Mühendisliği", fontSize = 14.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "AI & Mobile Developer",
                fontSize = 13.sp,
                color = Color(0xFF666666),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
@Composable
fun ContactMe(email: String, linkedin: String) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF3E5F5),
        shadowElevation = 4.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "Contact Me",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "📧 $email",
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "🔗 $linkedin",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}
@Composable
fun SkillItem(
    skill: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(50.dp),
        color = if (isSelected) Color(0xFFCE93D8) else Color(0xFFEDE7F6),
        shadowElevation = 3.dp
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(12.dp),
            color = Color(0xFF4A148C),
            fontWeight = FontWeight.Medium
        )
    }
}
