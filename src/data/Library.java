package data;
import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable{
	private static final long serialVersionUID = -7323573695156884717L;
	
		public static final int INITIAL_CAPACITY = 1;
		private Publication[] publications;
		private int publicationsNumber;
		
		public int getPublicationsNumber(){
			return publicationsNumber;
		}
		
		public Publication[] getPublications(){
			return publications;
		}

		
		public Library(){
			publications = new Publication[INITIAL_CAPACITY];
		}
		
		
		public void addPublication(Publication pub) throws ArrayIndexOutOfBoundsException{
			if(publicationsNumber == publications.length){
				publications = Arrays.copyOf(publications, publications.length*2);
			} 
			publications[publicationsNumber] = pub;
			publicationsNumber++;
		}
		
		public void removePublication(Publication pub){
			if(pub == null)
				return;
			
			final int NOT_FOUND = -1;
			int found = NOT_FOUND;
			int i = 0;
			while(i<publications.length && found == NOT_FOUND){
				if(pub.equals(publications[i])){
					found = i;
				} else{
					i++;
				}
			}
			
			if(found!= NOT_FOUND){
				System.arraycopy(publications, found+1, publications, found, publications.length - found -1 );
				publicationsNumber--;
			}
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			for(int i= 0; i<publicationsNumber; i++){
				builder.append(publications[i]);
				builder.append("\n");
			}
			return builder.toString();
		}	

}
