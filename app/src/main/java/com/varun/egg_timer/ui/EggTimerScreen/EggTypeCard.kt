import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varun.egg_timer.models.EggType

//@Composable
//fun EggTypeCard(eggType: EggType, onClick: (EggType) -> Unit){
//
//    Card(
//        Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable { onClick(eggType) },
//        elevation = CardDefaults.cardElevation(6.dp)
//    ) {
//        Column(Modifier.padding(6.dp)) {
//            Text(eggType.name, style = MaterialTheme.typography.titleLarge)
//            Text(eggType.description, style = MaterialTheme.typography.bodyMedium)
//        }
//    }
//
//}

@Composable
fun EggTypeCard(eggType: EggType, onClick: (EggType) -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(eggType) }
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Timer, contentDescription = null)
            Spacer(Modifier.width(12.dp))
            Column {
                Text(eggType.name, style = MaterialTheme.typography.titleLarge)
                Text(eggType.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}