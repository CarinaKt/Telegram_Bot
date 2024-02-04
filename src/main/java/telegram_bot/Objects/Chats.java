package telegram_bot.Objects;

public class Chats {
    private Chat[] chats;

    public Chats(){

        this.chats = new Chat[0];
    }

    public void add( String name, String id ){

        if (!inArray(id)){
            // create a new array with one extra element
            Chat[] newChatIDs = new Chat[chats.length + 1];

            // copy the elements from the old array to the new array
            System.arraycopy(chats, 0, newChatIDs, 0, chats.length);

            // add a new ChatID object to the end of the new array
            newChatIDs[newChatIDs.length - 1] = new Chat(name, id);

            // update the reference to the new array
            chats = newChatIDs;
        }
    }

    private boolean inArray(String id){

        for (Chat chat : chats) {
            String cid = chat.getId();
            if (cid == id){
                return true;
            }
         }
        return false;
    }

    private int getIndex( String id ) throws Exception{
        int index = 0;
        for (Chat chat : chats) {
           String cid = chat.getId();
           if (cid == id){
            return index;
           }
           index ++;
        }
        throw new Exception("ID not in Array");
    }

    public void remove( String id ){
    
        try {
            // find the index of the object to be removed
            int indexToRemove = getIndex(id);

            // create a new array with one fewer element
            Chat[] newChatIDs = new Chat[chats.length - 1];

            // copy the elements from the old array to the new array, excluding the element to be removed
            System.arraycopy(chats, 0, newChatIDs, 0, indexToRemove);
            System.arraycopy(chats, indexToRemove + 1, newChatIDs, indexToRemove, chats.length - indexToRemove - 1);

            // update the reference to the new array
            chats = newChatIDs;
        } catch (Exception e) {
            // TODO: handle exception
        }
 
    }
    
    public Chat[] getChats() {
        return chats;
    }
}
