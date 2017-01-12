import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class WordCount {
	
	
	public static class Map{
		
		public ArrayList<Word> m;
		
		public Map(String src) throws IOException{
			 try {
				
				BufferedReader in = new BufferedReader(new FileReader(src));
				String line;
				String delimiters = "\\s|(?:\\.|\\,)(\\s)|(?:\\.|\\,|\\s)$";
				this.m = new ArrayList<Word>();
				
				while((line = in.readLine()) != null)
				{
					
				    //divide by words
				    String [] res  = line.split(delimiters);
				    for(String s : res){
				    	m.add(new Word(s,1));
				    }
				}
				
				in.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
	
	public static class Reduce{
		
		private static Map map;
		
		public Reduce(Map map){
					
			this.map = map;
	
			System.out.println(map.m.size());
			map.m.sort(new Comparator<Word>() {
				
				public int compare(Word w1, Word w2) {
					 return  w1.getWord().compareTo(w2.getWord());
				}
		    	});
			
			System.out.println("\n ##### FASE 1 - Map #####");		
			for(Word element: map.m){
				System.out.println(element.getWord());
				
			}
			
			
			System.out.println("\n ##### FASE 2 - Reduce #####");
			int count =1, ind=0;
			Word prevWord = map.m.remove(ind);

			while(!map.m.isEmpty()){
					if(!(prevWord.getWord().equals(map.m.get(ind).getWord()))){
						System.out.println(prevWord.getWord() +" : " +count);
						count =0;
					}
					prevWord = map.m.remove(ind);
					count++;
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		String source = (String)(System.getProperty("user.dir")+"\\prueba.txt");
		Map m = new Map(source);
		Reduce r = new Reduce(m);
		
		
		
	}
	
	

}


	
