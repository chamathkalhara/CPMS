package reserve;

import controller.*;
import java.util.*;

public class SlotReserver{
	
	public Map<String,ReservationController> reservedData=new HashMap<>();
	
	public boolean reserveSlot(String slotNumber,ReservationController reservationController){
		if(reservedData.containsKey(slotNumber)){
			return false;
		}else{
			reservedData.put(slotNumber,reservationController);
			return true;
		}
	}
	
	public boolean releseSlot(String slotNumber,ReservationController reservationController){
		if(reservedData.get(slotNumber)==reservationController){
			reservedData.remove(slotNumber);
			return true;
		}else{
			return false;
		}
	}
}
