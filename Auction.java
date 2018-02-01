import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            Bid bid = new Bid(bidder, value);
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot loteFinal = null;
        if((lotNumber >= 0) && (lotNumber < lots.size())) {           
            boolean loteEncontrado = false;
            int contadorDeLotes=0;
            while(contadorDeLotes<lots.size() && loteEncontrado == false){
                if(lots.get(contadorDeLotes).getNumber() == lotNumber) {
                    loteFinal = lots.get(contadorDeLotes);
                    loteEncontrado = true;
                }
                else if (contadorDeLotes == lots.size()-1 ){
                    System.out.println("El numero de lote: " + lotNumber +
                        " No existe");

                }
                contadorDeLotes= contadorDeLotes + 1;
            }
        }
        else {
            System.out.println("El numero de lote: " + lotNumber +
                " No existe");           
        }
        return loteFinal;
    }

    /**
     * Metodo close  itera a traves de la coleccion de lotes e imprime los detalles de todos 
     * los lotes.
     */
    public void close() 
    {
        for(Lot lotesDePujas : lots) {
            Bid highestBid = lotesDePujas.getHighestBid();
            if(highestBid != null) {
                System.out.println(lotesDePujas);
            }
            else {
                System.out.println(lotesDePujas.getNumber() + ": El Lote no tiene pujas");
            }
        }
    }

    /**
     * metodo para almacenar los lotes no vendidos en un arraylist nuevo y buscamos los lotes
     * con puja maxima null
     */
    public ArrayList<Lot> getunsold()
    {
        ArrayList<Lot> unsoldLots = new ArrayList<Lot>();
        if(0 < lots.size()){
            for (Lot lotesDePujas : lots){
                if(lotesDePujas.getHighestBid()== null)
                {
                    unsoldLots.add(new Lot(lotesDePujas.getNumber(),lotesDePujas.getDescription()));
                }
            }
        }        return unsoldLots;
    }

    /**
     * Metodo para eliminar el lote con el numero de lote pasado por parametro
     */
    public Lot removeLot(int numeroDeLote){
        Lot loteEliminado = getLot(numeroDeLote);
        lots.remove(loteEliminado);
        return loteEliminado;
    }
}

