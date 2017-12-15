package data;
import java.io.Serializable;

public class Library implements Serializable{
	private static final long serialVersionUID = -7323573695156884717L;
	
		public static final int MAX_PUBLICATIONS = 2000;
		private Publication[] publications;
		private int publicationsNumber;
		
		public int getPublicationsNumber(){
			return publicationsNumber;
		}
		
		public Publication[] getPublications(){
			return publications;
		}

		
		public Library(){
			publications = new Publication[MAX_PUBLICATIONS];
		}
		
		
		public void addPublication(Publication pub) throws ArrayIndexOutOfBoundsException{
			if(publicationsNumber == MAX_PUBLICATIONS){
				throw new ArrayIndexOutOfBoundsException("MAX_PUBLICATIONS" + MAX_PUBLICATIONS);
			} 
			publications[publicationsNumber] = pub;
			publicationsNumber++;
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
