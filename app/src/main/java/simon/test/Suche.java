package simon.test;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Geocoder;
import android.location.Address;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import com.google.android.gms.maps.CameraUpdate;

/**
 *
 * Activity "nach" der Main Activity und dem Eingeben des zu suchenden Kennzeichens.
 * Dies wird durch den intent weitergegeben und hier schließlich verarbeitet (switch/case).
 *
 */
public class Suche extends AppCompatActivity implements OnMapReadyCallback {

    String ausgabe = "";
    private GoogleApiClient client;
    private SharedPreferences speicher, counter;
    private SharedPreferences.Editor editor, editor2;
    private Integer i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suche);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Die entsprechenden SharedPreference-Daten müssen gefunden werden und zugänglich gemacht werden
        //"Speicher" ist für die Ergebnisse, Counter nur für den Zähler, an dem man die Daten nachher wieder auslesen kann
        speicher = getApplicationContext().getSharedPreferences("Data", 0);
        editor = speicher.edit();
        counter =getApplicationContext().getSharedPreferences("Counter", 0);
        editor2 = counter.edit();

        i = counter.getInt("counter", 0);

            //Fragment für Google Maps Einbindung, sehr einfach gehalten
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        //Der "Return" button, dieser führt zurück zur Mainactivity. Die Suche wird gespeichert.
        Button btn = (Button) findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Suche.this, MainActivity.class);
                //Diese Flag bereinigt alle anderen Activities außer die, auf die der Intent geht.
                //Dies ist eine sehr einfache Art die "Backnavigation" zu realisieren.
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        //Hier wird die Benutzereingabe aus dem Intent gezogen.
        String eingabe = "";
        Bundle extras = getIntent().getExtras();
        eingabe = extras.getString("EingabeString");
        //Textfeld, in welchem nachher die Ausgabe stehen soll.
        TextView textView = (TextView) findViewById(R.id.ausgabe2);
       /** TextView maps = (TextView) findViewById(R.id.maps_front);
        if(!Einstellungen.maps_enabled){
            maps.setVisibility(View.VISIBLE);
        }*/
      /**  ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PLATE_NUMBER, new String("ZZ"));
        values.put(DbHelper.COLUMN_RESULT, new String("Zeitz in Sachsen-Anhalt"));
*/



        switch (eingabe) {
            /** Section 0*/
            case "0":
                ausgabe = "Auto der Behörden(Bundespräsident etc.)";
                break;
            case "1":
                ausgabe = "Auto des Bundespräsidenten(Kennzeichen 1-1)";
                break;
            /** Section A*/
            case "A":
                ausgabe = "Augsburg in Bayern";
                break;
            case "AA":
                ausgabe = "Aalen in Baden-Württemberg";
                break;
            case "AB":
                ausgabe = "Aschaffenburg in Bayern";
                break;
            case "ABG":
                ausgabe = "Altenburg in Thüringen";
                break;
            case "ABI":
                ausgabe = "Anhalt-Bitterfeld in Sachsen-Anhalt";
                break;
            case "AC":
                ausgabe = "Aachen in Nordrhein-Westfalen";
                break;
            case "AE":
                ausgabe = "Auerbach/Vogtl. in Sachsen";
                break;
            case "AH":
                ausgabe = "Ahaus in Nordrhein-Westfalen";
                break;
            case "AIB":
                ausgabe = "Bad Aibling in Bayern";
                break;
            case "AIC":
                ausgabe = "Aichach in Bayern";
                break;
            case "AK":
                ausgabe = "Altenkirchen(Westerwald) in Rheinland-Pfalz";
                break;
            case "ALF":
                ausgabe = "Alfeld(Leine) in Niedersachsen";
                break;
            case "ALZ":
                ausgabe = "Alzenau in Bayern";
                break;
            case "AM":
                ausgabe = "Amberg in Bayern";
                break;
            case "AN":
                ausgabe = "Ansbach in Bayern";
                break;
            case "ANA":
                ausgabe = "Annaberg-Buchholz in Sachsen";
                break;
            case "ANG":
                ausgabe = "Angermünde in Brandenburg";
                break;
            case "ANK":
                ausgabe = "Anklam in Mecklenburg-Vorpommern";
                break;
            case "AÖ":
                ausgabe = "Altötting in Bayern";
                break;
            case "AP":
            case "APD":
                ausgabe = "Apolda in Thüringen";
                break;
            case "ARN":
                ausgabe = "Arnstadt in Thüringen";
                break;
            case "ART":
                ausgabe = "Artern in Thüringen";
                break;
            case "AS":
                ausgabe = "Amberg-Sulzbach in Bayern";
                break;
            case "ASL":
                ausgabe = "Aschersleben in Sachsen-Anhalt";
                break;
            case "ASZ":
                ausgabe = "Aue-Schwarzenberg in Sachsen";
                break;
            case "AT":
                ausgabe = "Altentreptow in Mecklenburg-Vorpommern";
                break;
            case "AUR":
                ausgabe = "Aurich in Niedersachsen";
                break;
            case "AW":
                ausgabe = "Ahrweiler in Rheinland-Pfalz";
                break;
            case "AZ":
                ausgabe = "Alzey in Rheinland-Pfalz";
                break;
            case "AZE":
                ausgabe = "Anhalt-Zerbst in Sachsen-Anhalt";
                break;
            /** Section B*/
            case "B":
                ausgabe = "Berlin/ Diplomatenkennzeichen (wenn danach eine Nummer folgt)";
                break; /**Problem bei Maps*/
            case "BA":
                ausgabe = "Bamberg in Bayern";
                break;
            case "BAD":
                ausgabe = "Baden-Baden in Baden-Württemberg";
                break;
            case "BAR":
                ausgabe = "Barnim in Brandenburg";
                break;
            case "BB":
                ausgabe = "Böblingen in Baden-Württemberg";
                break;
            case "BBG":
                ausgabe = "Bernburg(Saale) in Sachsen-Anhalt";
                break;
            case "BBL":
                ausgabe = "Brandenburg/ Landesregierung und Landtag";
                break;
            case "BC":
                ausgabe = "Biberach an der Riß in Baden-Württemberg";
                break;
            case "BCH":
                ausgabe = "Buchen(Odenwald) in Baden-Württemberg";
                break;
            case "BD":
                ausgabe = "Auto des Bundes";
                break;
            case "BE":
                ausgabe = "Beckum in Nordrhein-Westfalen";
                break;
            case "BED":
                ausgabe = "Brand-Erbisdorf in Sachsen";
                break;
            case "BER":
                ausgabe = "Bernau bei Berlin in Brandenburg";
                break;
            case "BF":
                ausgabe = "Burgsteinfurt in Nordrhein-Westfalen";
                break;
            case "BGD":
                ausgabe = "Berchtesgaden in Bayern";
                break;
            case "BGL":
                ausgabe = "Berchtesgadener Land in Bayern";
                break;
            case "BH":
                ausgabe = "Bühl in Baden-Württemberg";
                break;
            case "BI":
                ausgabe = "Bielefeld in Nordrhein-Westfalen";
                break;
            case "BID":
                ausgabe = "Biedenkopf in Hessen";
                break;
            case "BIN":
                ausgabe = "Bingen in Rheinland-Pfalz";
                break;
            case "BIR":
                ausgabe = "Birkenfeld in Rheinland-Pfalz";
                break;
            case "BIT":
                ausgabe = "Bitburg in Rheinland-Pfalz";
                break;
            case "BIW":
                ausgabe = "Bischofswerda in Sachsen";
                break;
            case "BK":
                ausgabe = "Backnang in Baden-Württemberg";
                break;
            case "BKS":
                ausgabe = "Bernkastel in Rheinland-Pfalz";
                break;
            case "BL":
                ausgabe = "Balingen in Baden-Württemberg";
                break;
            case "BLB":
                ausgabe = "Berleburg in Nordrhein-Westfalen";
                break;
            case "BLK":
                ausgabe = "Burgenlandkreis in Sachsen-Anhalt";
                break;
            case "BM":
                ausgabe = "Bergheim in Nordrhein-Westfalen";
                break;
            case "BN":
                ausgabe = "Bonn in Nordrhein-Westfalen";
                break;
            case "BNA":
                ausgabe = "Borna in Sachsen";
                break;
            case "BO":
                ausgabe = "Bochum in Nordrhein-Westfalen";
                break;
            case "BÖ":
                ausgabe = "Börde in Sachsen-Anhalt";
                break;
            case "BOH":
                ausgabe = "Bocholt in Nordrhein-Westfalen";
                break;
            case "BOR":
                ausgabe = "Borken in Nordrhein-Westfalen";
                break;
            case "BOT":
                ausgabe = "Bottrop in Nordrhein-Westfalen";
                break;
            case "BP":
                ausgabe = "Bundespolizei bundesweit";
                break;
            case "BRA":
                ausgabe = "Brake in Niedersachsen";
                break;
            case "BRB":
                ausgabe = "Brandenburg in Brandenburg";
                break;
            case "BRG":
                ausgabe = "Burg in Sachsen-Anhalt";
                break;
            case "BRK":
                ausgabe = "Brückenau in Bayern";
                break;
            case "BRL":
                ausgabe = "Braunlage in Niedersachsen";
                break;
            case "BRV":
                ausgabe = "Bremervörde in Niedersachsen";
                break;
            case "BS":
                ausgabe = "Braunschweig in Niedersachsen";
                break;
            case "BT":
                ausgabe = "Bayreuth in Bayern";
                break;
            case "BTF":
                ausgabe = "Bitterfeld in Sachsen-Anhalt";
                break;
            case "BÜD":
                ausgabe = "Büdingen in Hessen";
                break;
            case "BUL":
                ausgabe = "Burglengenfeld in Bayern";
                break;
            case "BÜR":
                ausgabe = "Büren in Nordrhein-Westfalen";
                break;
            case "BÜS":
                ausgabe = "Büsingen in Baden-Württemberg";
                break;
            case "BÜZ":
                ausgabe = "Bützow in Mecklenburg-Vorpommern";
                break;
            case "BZ":
                ausgabe = "Bautzen in Sachsen";
                break;
            /** Section C*/
            case "C":
                ausgabe = "Chemnitz in Sachsen";
                break;
            case "CA":
                ausgabe = "Calau in Brandenburg ";
                break;
            case "CAS":
                ausgabe = "Castrop in Nordrhein-Westfalen";
                break;
            case "CB":
                ausgabe = "Cottbus in Brandenburg";
                break;
            case "CE":
                ausgabe = "Celle in Niedersachsen";
                break;
            case "CHA":
                ausgabe = "Cham in Bayern";
                break;
            case "CLP":
                ausgabe = "Cloppenburg in Niedersachsen";
                break;
            case "CLZ":
                ausgabe = "Clausthal-Zellerfeld in Niedersachsen";
                break;
            case "CO":
                ausgabe = "Coburg in Bayern";
                break;
            case "COC":
                ausgabe = "Cochem in Rheinland-Pfalz";
                break;
            case "COE":
                ausgabe = "Coesfeld in Nordrhein-Westfalen";
                break;
            case "CR":
                ausgabe = "Crailsheim in Baden-Württemberg";
                break;
            case "CUX":
                ausgabe = "Cuxhaven in Niedersachsen";
                break;
            case "CW":
                ausgabe = "Calw in Baden-Württemberg";
                break;
            /** Section D*/
            case "D":
                ausgabe = "Düsseldorf in Nordrhein-Westfalen";
                break;
            case "DA":
                ausgabe = "Darmstadt in Hessen";
                break;
            case "DAH":
                ausgabe = "Dachau in Bayern";
                break;
            case "DAN":
                ausgabe = "Dannenberg in Niedersachsen";
                break;
            case "DAU":
                ausgabe = "Daun in Rheinland-Pfalz";
                break;
            case "DBR":
                ausgabe = "Doberan/Rostock in Mecklenburg-Vorpommern";
                break;
            case "DD":
                ausgabe = "Dresden(Stadt/Polizei) in Sachsen";
                break;
            case "DE":
                ausgabe = "Dessau(-Roßlau) in Sachsen-Anhalt";
                break;
            case "DEG":
                ausgabe = "Deggendorf in Bayern";
                break;
            case "DEL":
                ausgabe = "Delmenhorst in Niedersachsen";
                break;
            case "DGF":
                ausgabe = "Dingolfing in Bayern";
                break;
            case "DH":
                ausgabe = "Diepholz in Niedersachsen";
                break;
            case "DI":
                ausgabe = "Dieburg in Hessen";
                break;
            case "DIL":
                ausgabe = "Dillenburg in Hessen";
                break;
            case "DIN":
                ausgabe = "Dinslaken in Nordrhein-Westfalen";
                break;
            case "DIZ":
                ausgabe = "Diez in Rheinland-Pfalz";
                break;
            case "DKB":
                ausgabe = "Dinkelsbühl in Bayern";
                break;
            case "DL":
                ausgabe = "Döbeln in Sachsen";
                break;
            case "DLG":
                ausgabe = "Dillingen an der Donau in Bayern";
                break;
            case "DM":
                ausgabe = "Demmin in Mecklenburg-Vorpommern";
                break;
            case "DN":
                ausgabe = "Düren in Nordrhein-Westfalen";
                break;
            case "DO":
                ausgabe = "Dortmund in Nordrhein-Westfalen";
                break;
            case "DON":
                ausgabe = "Donauwörth in Bayern";
                break;
            case "DU":
                ausgabe = "Duisburg in Nordrhein-Westfalen";
                break;
            case "DUD":
                ausgabe = "Duderstadt in Niedersachsen";
                break;
            case "DÜW":
                ausgabe = "Dürkheim and der Weinstraße in Rheinland-Pfalz";
                break;
            case "DW":
                ausgabe = "Dippoldiswalde in Sachsen";
                break;
            case "DZ":
                ausgabe = "Delitzsch in Sachsen";
                break;
            /** Section E*/
            case "E":
                ausgabe = "Essen in Nordrhein-Westfalen";
                break;
            case "EA":
                ausgabe = "Eisenach in Thüringen";
                break;
            case "EB":
                ausgabe = "Eilenburg in Sachsen";
                break;
            case "EBE":
                ausgabe = "Ebersberg in Bayern";
                break;
            case "EBN":
                ausgabe = "Ebern in Bayern";
                break;
            case "EBS":
                ausgabe = "Ebermannstadt in Bayern";
                break;
            case "ECK":
                ausgabe = "Eckernförde in Schleswig-Holstein";
                break;
            case "ED":
                ausgabe = "Erding in Bayern";
                break;
            case "EE":
                ausgabe = "Elbe-Elster in Brandenburg";
                break;
            case "EF":
                ausgabe = "Erfurt in Thüringen";
                break;
            case "EG":
                ausgabe = "Eggenfelden in Bayern";
                break;
            case "EH":
                ausgabe = "Eisenhüttenstadt in Brandenburg";
                break;
            case "EI":
                ausgabe = "Eichstätt in Bayern";
                break;
            case "EIC":
                ausgabe = "Eichsfeld in Thüringen";
                break;
            case "EIL":
                ausgabe = "Elsleben in Sachsen-Anhalt";
                break;
            case "EIN":
                ausgabe = "Einbeck in Niedersachsen";
                break;
            case "EIS":
                ausgabe = "Eisenberg in Thüringen";
                break;
            case "EL":
                ausgabe = "Emsland in Niedersachsen";
                break;
            case "EM":
                ausgabe = "Emmendingen in Baden-Württemberg";
                break;
            case "EMD":
                ausgabe = "Emden in Niedersachsen";
                break;
            case "EMS":
                ausgabe = "Ems in Rheinland-Pfalz";
                break;
            case "EN":
                ausgabe = "Ennepe in Nordrhein-Westfalen";
                break;
            case "ER":
                ausgabe = "Erlangen in Bayern";
                break;
            case "ERB":
                ausgabe = "Erbach in Hessen";
                break;
            case "ERH":
                ausgabe = "Erlangen-Höchstadt in Bayern";
                break;
            case "ERK":
                ausgabe = "Erkelenz in Nordrhein-Westfalen";
                break;
            case "ERZ":
                ausgabe = "Erzgebirge in Sachsen";
                break;
            case "ES":
                ausgabe = "Esslingen in Baden-Württemberg";
                break;
            case "ESB":
                ausgabe = "Eschenbach in Bayern";
                break;
            case "ESW":
                ausgabe = "Eschenwege in Hessen";
                break;
            case "EU":
                ausgabe = "Euskirchen in Nordrhein-Westfalen";
                break;
            case "EW":
                ausgabe = "Ebernwalde in Brandenburg";
                break;
            /** Section F*/
            case "F":
                ausgabe = "Frankfurt am Main in Hessen";
                break;
            case "FB":
                ausgabe = "Friedberg in Hessen";
                break;
            case "FD":
                ausgabe = "Fulda in Hessen";
                break;
            case "FDB":
                ausgabe = "Friedberg in Bayern";
                break;
            case "FDS":
                ausgabe = "Freudenstadt in Baden-Württemberg";
                break;
            case "FEU":
                ausgabe = "Feuchtwangen in Bayern";
                break;
            case "FF":
                ausgabe = "Frankfurt(Oder) in Brandenburg";
                break;
            case "FFB":
                ausgabe = "Fürstenfeldbruck in Bayern";
                break;
            case "FG":
                ausgabe = "Freiberg in Sachsen";
                break;
            case "FI":
                ausgabe = "Finsterwalde in Brandenburg";
                break;
            case "FKB":
                ausgabe = "Frankenberg in Hessen";
                break;
            case "FL":
                ausgabe = "Flensburg in Schleswig-Holstein";
                break;
            case "FLÖ":
                ausgabe = "Flöha in Sachsen";
                break;
            case "FN":
                ausgabe = "Friedrichshafen in Baden-Württemberg";
                break;
            case "FO":
                ausgabe = "Forchheim in Bayern";
                break;
            case "FOR":
                ausgabe = "Forst in Brandenburg";
                break;
            case "FR":
                ausgabe = "Freiburg in Baden-Württemberg";
                break;
            case "FRG":
                ausgabe = "Freyung-Grafenau in Bayern";
                break;
            case "FRI":
                ausgabe = "Friesland in Niedersachsen";
                break;
            case "FRW":
                ausgabe = "Freienwalde in Brandenburg";
                break;
            case "FS":
                ausgabe = "Freising in Bayern";
                break;
            case "FT":
                ausgabe = "Frankenthal in Rheinland-Pfalz";
                break;
            case "FTL":
                ausgabe = "Freital in Sachsen";
                break;
            case "FÜ":
                ausgabe = "Fürth in Bayern";
                break;
            case "FÜS":
                ausgabe = "Füssen in Bayern";
                break;
            case "FZ":
                ausgabe = "Fritzlar in Hessen";
                break;
            /** Section G*/
            case "G":
                ausgabe = "Gera in Thüringen";
                break;
            case "GA":
                ausgabe = "Gardelegen in Sachsen-Anhalt";
                break;
            case "GAN":
                ausgabe = "Gandersheim in Niedersachsen";
                break;
            case "GAP":
                ausgabe = "Garmisch-Partenkirchen in Bayern";
                break;
            case "GC":
                ausgabe = "Glauchau in Sachsen";
                break;
            case "GD":
                ausgabe = "Gmünd in Baden-Württemberg";
                break;
            case "GDB":
                ausgabe = "Gadebusch in Mecklenburg-Vorpommern";
                break;
            case "GE":
                ausgabe = "Gelsenkirchen in Nordrhein-Westfalen";
                break;
            case "GEL":
                ausgabe = "Geldern in Norhrhein-Westfalen";
                break;
            case "GEO":
                ausgabe = "Gerolzhofen in Bayern";
                break;
            case "GER":
                ausgabe = "Germersheim in Rheinland-Pfalz";
                break;
            case "GF":
                ausgabe = "Gifhorn in Niedersachsen";
                break;
            case "GG":
                ausgabe = "Groß-Gerau in Hessen";
                break;
            case "GHA":
                ausgabe = "Geithain in Sachsen";
                break;
            case "GHC":
                ausgabe = "Gräfenheinichen in Sachsen-Anhalt";
                break;
            case "GI":
                ausgabe = "Gießen in Hessen";
                break;
            case "GK":
                ausgabe = "Geilenkirchen in Nordrhein-Westfalen";
                break;
            case "GL":
                ausgabe = "Gladbach in Nordrhein-Westfalen";
                break;
            case "GLA":
                ausgabe = "Gladbeck in Nordrhein-Westfalen";
                break;
            case "GM":
                ausgabe = "Gummersbach in Nordrhein-Westfalen";
                break;
            case "GMN":
                ausgabe = "Grimmen in Mecklenburg-Vorpommern";
                break;
            case "GN":
                ausgabe = "Gelnhausen in Hessen";
                break;
            case "GNT":
                ausgabe = "Genthin in Sachsen-Anhalt";
                break;
            case "GÖ":
                ausgabe = "Göttingen in Niedersachsen";
                break;
            case "GOA":
                ausgabe = "Goar in Rheinland-Pfalz";
                break;
            case "GP":
                ausgabe = "Göppingen in Baden-Württemberg";
                break;
            case "GR":
                ausgabe = "Görlitz in Sachsen";
                break;
            case "GRA":
                ausgabe = "Gradfenau in Bayern";
                break;
            case "GRH":
                ausgabe = "Großenhain in Bayern";
                break;
            case "GRI":
                ausgabe = "Griesbach in Bayern";
                break;
            case "GRM":
                ausgabe = "Grimma in Sachsen";
                break;
            case "GRZ":
                ausgabe = "Greiz in Thüringen";
                break;
            case "GS":
                ausgabe = "Goslar in Niedersachsen";
                break;
            case "GT":
                ausgabe = "Gütersloh in Nordrhein-Westfalen";
                break;
            case "GTH":
                ausgabe = "Gotha in Thüringen";
                break;
            case "GÜ":
                ausgabe = "Güstrow in Mecklenburg-Vorpommern";
                break;
            case "GUB":
                ausgabe = "Guben in Brandenburg";
                break;
            case "GUN":
                ausgabe = "Gunzenhausen in Bayern";
                break;
            case "GV":
                ausgabe = "Grevenbroich in Nordrhein-Westfalen";
                break;
            case "GVM":
                ausgabe = "Grevesmühlen in Mecklenburg-Vorpommern";
                break;
            case "GW":
                ausgabe = "Greifswald in Mecklenburg-Vorpommern";
                break;
            case "GZ":
                ausgabe = "Günzburg in Bayern";
                break;
            /** Section H*/
            case "H":
                ausgabe = "Hannover in Niedersachsen";
                break;
            case "HA":
                ausgabe = "Hagen in Nordrhein-Westfalen";
                break;
            case "HAB":
                ausgabe = "Hammelburg in Sachsen-Anhalt";
                break;
            case "HAL":
                ausgabe = "Hallo in Niedersachsen";
                break;
            case "HAM":
                ausgabe = "Hamm in Nordrhein-Westfalen";
                break;
            case "HAS":
                ausgabe = "Hassfurt in Bayern";
                break;
            case "HB":
                ausgabe = "Bremen in Bremen";
                break;
            case "HBN":
                ausgabe = "Hildburghausen in Thüringen";
                break;
            case "HBS":
                ausgabe = "Halberstadt in Sachsen-Anhalt";
                break;
            case "HC":
                ausgabe = "Hainichen in Sachsen";
                break;
            case "HCH":
                ausgabe = "Hechingen in Baden-Württemberg";
                break;
            case "HD":
                ausgabe = "Heidelberg in Baden-Württemberg";
                break;
            case "HDH":
                ausgabe = "Heidenheim in Baden-Württemberg";
                break;
            case "HDL":
                ausgabe = "Haldensleben in Sachsen-Anhalt";
                break;
            case "HE":
                ausgabe = "Helmstedt in Niedersachsen";
                break;
            case "HEB":
                ausgabe = "Hersbruck in Bayern";
                break;
            case "HEF":
                ausgabe = "Hersfeld in Hessen";
                break;
            case "HEI":
                ausgabe = "Helde in Schleswig-Holstein";
                break;
            case "HEL":
                ausgabe = "Hessischer Landtag(Landesregierung) in Hessen";
                break;
            case "HER":
                ausgabe = "Herne in Nordrhein-Westfalen";
                break;
            case "HET":
                ausgabe = "Hettstedt in Sachsen-Anhalt";
                break;
            case "HF":
                ausgabe = "Herford in Nordrhein-Westfalen";
                break;
            case "HG":
                ausgabe = "Homburg in Hessen";
                break;
            case "HGN":
                ausgabe = "Hagenau in Mecklenburg-Vorpommern";
                break;
            case "HGW":
                ausgabe = "Hansestadt Greifswald in Mecklenburg-Vorpommern";
                break;
            case "HH":
                ausgabe = "Hamburg in Hamburg";
                break;
            case "HHM":
                ausgabe = "Hohenmölsen in Sachsen-Anhalt";
                break;
            case "HI":
                ausgabe = "Hildesheim in Niedersachsen";
                break;
            case "HIG":
                ausgabe = "Heiligenstadt in Thüringen";
                break;
            case "HIP":
                ausgabe = "Hilpoltstein in Bayern";
                break;
            case "HK":
                ausgabe = "Heidekreis in Niedersachsen";
                break;
            case "HL":
                ausgabe = "Hansestadt Lübeck in Schleswig-Holstein";
                break;
            case "HM":
                ausgabe = "Hammeln in Niedersachsen";
                break;
            case "HMÜ":
                ausgabe = "(Hannoversch) Münden in Niedersachsen";
                break;
            case "HN":
                ausgabe = "Heilbronn in Baden-Württemberg";
                break;
            case "HO":
                ausgabe = "Hof in Bayern";
                break;
            case "HOG":
                ausgabe = "Hofgeismar in Hessen";
                break;
            case "HOH":
                ausgabe = "Hofheim in Bayern";
                break;
            case "HOL":
                ausgabe = "Holzminden in Niedersachsen";
                break;
            case "HOM":
                ausgabe = "Homburg im Saarland";
                break;
            case "HOR":
                ausgabe = "Horb in Baden-Württemberg";
                break;
            case "HÖS":
                ausgabe = "Hochstadt in Bayern";
                break;
            case "HOT":
                ausgabe = "Hohenstein in Sachsen";
                break;
            case "HP":
                ausgabe = "Heppenheim in Hessen";
                break;
            case "HR":
                ausgabe = "Homberg in Hessen";
                break;
            case "HRO":
                ausgabe = "Hansestadt Rostock in Mecklenburg-Vorpommern";
                break;
            case "HS":
                ausgabe = "Heinsberg in Norhrhein-Westfalen";
                break;
            case "HSK":
                ausgabe = "Hochsauerlandkreis in Nordrhein-Westfalen";
                break;
            case "HU":
                ausgabe = "Hanau in Hessen";
                break;
            case "HV":
                ausgabe = "Havelberg in Sachsen-Anhalt";
                break;
            case "HVL":
                ausgabe = "Hevelland in Brandenburg";
                break;
            case "HWI":
                ausgabe = "Hansestadt Wismar in Mecklenburg-Vorpommern";
                break;
            case "HX":
                ausgabe = "Höxter in Nordrhein-Westfalen";
                break;
            case "HY":
                ausgabe = "Hoyerswerda in Sachsen";
                break;
            case "HZ":
                ausgabe = "Harz in Sachsen-Anhalt";
                break;
            /** Section I*/
            case "IGB":
                ausgabe = "Ingbert im Saarland";
                break;
            case "IK":
                ausgabe = "Ilm-Kreis in Thüringen";
                break;
            case "IL":
                ausgabe = "Ilmenau in Thüringen";
                break;
            case "ILL":
                ausgabe = "Illertissen in Bayern";
                break;
            case "IN":
                ausgabe = "Ingolstadt in Bayern";
                break;
            case "IZ":
                ausgabe = "Itzehoe in Schleswig-Holstein";
                break;
            /**Section J*/
            case "J":
                ausgabe = "Jena in Thüringen";
                break;
            case "JE":
                ausgabe = "Jessen in Sachsen-Anhalt";
                break;
            case "JL":
                ausgabe = "Jerichower Land in Sachsen-Anhalt";
                break;
            case "JÜL":
                ausgabe = "Jülich in Nordrhein-Westfalen";
                break;
            /**Section K*/
            case "K":
                ausgabe = "Köln in Nordrhein-Westfalen";
                break;
            case "KA":
                ausgabe = "Karlsruhe in Baden-Württemberg";
                break;
            case "KB":
                ausgabe = "Korbach in Hessen";
                break;
            case "KC":
                ausgabe = "Kronach in Bayern";
                break;
            case "KE":
                ausgabe = "Kempten in Bayern";
                break;
            case "KEH":
                ausgabe = "Kelheim in Bayern";
                break;
            case "KEL":
                ausgabe = "Kehl in Baden-Württemberg";
                break;
            case "KEM":
                ausgabe = "Kemnath in Bayern";
                break;
            case "KF":
                ausgabe = "Kaufbeuren in Bayern";
                break;
            case "KG":
                ausgabe = "Kissingen in Bayern";
                break;
            case "KH":
                ausgabe = "Kreuznach in Rheinland-Pfalz";
                break;
            case "KI":
                ausgabe = "Kiel in Schleswig-Holstein";
                break;
            case "KIB":
                ausgabe = "Kirchheim-Bolanden in Rheinland-Pfalz";
                break;
            case "KK":
                ausgabe = "Kempten-Krefeld in Nordrhein-Westfalen";
                break;
            case "KL":
                ausgabe = "Kaiserslautern in Rheinland-Pfalz";
                break;
            case "KLE":
                ausgabe = "Kleve in Nordrhein-Westfalen";
                break;
            case "KLZ":
                ausgabe = "Klötze in Sachsen-Anhalt";
                break;
            case "KM":
                ausgabe = "Kamenz in Sachsen";
                break;
            case "KN":
                ausgabe = "Konstanz in Baden-Württemberg";
                break;
            case "KO":
                ausgabe = "Koblenz in Rheinland-Pfalz";
                break;
            case "KÖN":
                ausgabe = "Königshofen in Bayern";
                break;
            case "KÖT":
                ausgabe = "Köthen in Sachsen-Anhalt";
                break;
            case "KÖZ":
                ausgabe = "Kötzting in Bayern";
                break;
            case "KR":
                ausgabe = "Krefeld in Nordrhein-Westfalen";
                break;
            case "KRU":
                ausgabe = "Krumbach in Bayern";
                break;
            case "KS":
                ausgabe = "Kassel in Hessen";
                break;
            case "KT":
                ausgabe = "Kitzingen in Bayern";
                break;
            case "KU":
                ausgabe = "Kulmbach in Bayern";
                break;
            case "KÜN":
                ausgabe = "Künzelsau in Baden-Württemberg";
                break;
            case "KUS":
                ausgabe = "Kusel in Rheinland-Pfalz";
                break;
            case "KW":
                ausgabe = "Königs Wusterhausen in Brandenburg";
                break;
            case "KY":
                ausgabe = "Kyritz in Brandenburg";
                break;
            case "KYF":
                ausgabe = "Kyffhäuser in Thüringen";
                break;
            /** Section L*/
            case "L":
                ausgabe = "Leipzig in Sachsen";
                break;
            case "LA":
                ausgabe = "Landshut in Bayern";
                break;
            case "LAN":
                ausgabe = "Landau in Bayern";
                break;
            case "LAU":
                ausgabe = "Lauf in Bayern";
                break;
            case "LB":
                ausgabe = "Ludwigsburg in Baden-Württemberg";
                break;
            case "LBS":
                ausgabe = "Lobenstein in Thüringen";
                break;
            case "LBZ":
                ausgabe = "Lübz in Mecklenburg-Vorpommern";
                break;
            case "LC":
                ausgabe = "Luckau in Brandenburg";
                break;
            case "LD":
                ausgabe = "Landau in Rheinland-Pfalz";
                break;
            case "LDK":
                ausgabe = "Lahn-Dill-Kreis in Hessen";
                break;
            case "LDS":
                ausgabe = "Landkreis Dahme-Spreewald in Brandenburg";
                break;
            case "LEO":
                ausgabe = "Leonberg in Baden-Württemberg";
                break;
            case "LER":
                ausgabe = "Leer in Niedersachsen";
                break;
            case "LEV":
                ausgabe = "Leverkusen in Nordrhein-Westfalen";
                break;
            case "LF":
                ausgabe = "Laufen in Bayern";
                break;
            case "LG":
                ausgabe = "Lüneburg in Niedersachsen";
                break;
            case "LH":
                ausgabe = "Lüdinghausen in Nordrhein-Westfalen";
                break;
            case "LI":
                ausgabe = "Lindau in Bayern";
                break;
            case "LIB":
                ausgabe = "Liebenwerda in Brandenburg";
                break;
            case "LIF":
                ausgabe = "Lichtenfels in Bayern";
                break;
            case "LIP":
                ausgabe = "Lippe in Nordrhein-Westfalen";
                break;
            case "LL":
                ausgabe = "Landsberg am Lech in Bayern";
                break;
            case "LM":
                ausgabe = "Limburg in Hessen";
                break;
            case "LN":
                ausgabe = "Lübben in Brandenburg";
                break;
            case "LÖ":
                ausgabe = "Lörrach in Baden-Württemberg";
                break;
            case "LÖB":
                ausgabe = "Löbau in Sachsen";
                break;
            case "LOS":
                ausgabe = "Landkreis Oder-Spree in Brandenburg";
                break;
            case "LP":
                ausgabe = "Lipstadt in Nordrhein-Westfalen";
                break;
            case "LR":
                ausgabe = "Lahr in Baden-Württemberg";
                break;
            case "LRO":
                ausgabe = "Landkreis Rostock in Mecklenburg-Vorpommern";
                break;
            case "LSA":
                ausgabe = "Land Sachsen-Anhalt";
                break;
            case "LSN":
                ausgabe = "Landtag Sachsen";
                break;
            case "LSZ":
                ausgabe = "Langensalza in Thüringen";
                break;
            case "LU":
                ausgabe = "Ludwigshafen am Rhein in Rheinland-Pfalz";
                break;
            case "LÜN":
                ausgabe = "Lünen in Nordrhein-Westfalen";
                break;
            case "LUP":
                ausgabe = "Ludwigslust-Parchim in Mecklenburg-Vorpommern";
                break;
            case "LWL":
                ausgabe = "Ludwigslust in Mecklenburg-Vorpommern";
                break;
            /** Section M*/
            case "M":
                ausgabe = "München in Bayern";
                break;
            case "MA":
                ausgabe = "Mannheim in Baden-Württemberg";
                break;
            case "MAB":
                ausgabe = "Marienberg in Sachsen";
                break;
            case "MAI":
                ausgabe = "Mainburg in Bayern";
                break;
            case "MAK":
                ausgabe = "Marktredwitz in Bayern";
                break;
            case "MAL":
                ausgabe = "Mallersdorf in Bayern";
                break;
            case "MB":
                ausgabe = "Miesbach in Bayern";
                break;
            case "MC":
                ausgabe = "Malchin in Mecklenburg-Vorpommern";
                break;
            case "MD":
                ausgabe = "Magdeburg in Sachsen-Anhalt";
                break;
            case "ME":
                ausgabe = "Mettmann in Nordrhein-Westfalen";
                break;
            case "MED":
                ausgabe = "Meldorf in Schleswig-Holstein";
                break;
            case "MEG":
                ausgabe = "Meisungen in Hessen";
                break;
            case "MEI":
                ausgabe = "Meißen in Sachsen";
                break;
            case "MEK":
                ausgabe = "Mittlerer Erzgebirgekreis in Sachsen";
                break;
            case "MER":
                ausgabe = "Merseburg in Sachsen-Anhalt";
                break;
            case "MET":
                ausgabe = "Mellrichstadt in Bayern";
                break;
            case "MG":
                ausgabe = "Mönchengladbach in Nordrhein-Westfalen";
                break;
            case "MGH":
                ausgabe = "Mergentheim in Baden-Württemberg";
                break;
            case "MGN":
                ausgabe = "Meiningen in Thüringen";
                break;
            case "MH":
                ausgabe = "Mülheim in Nordrhein-Westfalen";
                break;
            case "MHL":
                ausgabe = "Mühlhausen in Thüringen";
                break;
            case "MI":
                ausgabe = "Minden in Nordrhein-Westfalen";
                break;
            case "MIL":
                ausgabe = "Miltenberg in Bayern";
                break;
            case "MK":
                ausgabe = "Märkischer Kreis in Nordrhein-Westfalen";
                break;
            case "MKK":
                ausgabe = "Main-Kinzig-Kreis in Hessen";
                break;
            case "ML":
                ausgabe = "Mansfelder Land in Sachsen-Anhalt";
                break;
            case "MM":
                ausgabe = "Memmingen in Bayern";
                break;
            case "MN":
                ausgabe = "Mindelheim in Bayern";
                break;
            case "MO":
                ausgabe = "Moers in Nordrheinwestfalen";
                break;
            case "MOD":
                ausgabe = "Marktoberdorf in Bayern";
                break;
            case "MOL":
                ausgabe = "Märkisch-Oderland in Brandenburg";
                break;
            case "MON":
                ausgabe = "Monschau in Nordrhein-Westfalen";
                break;
            case "MOS":
                ausgabe = "Mosbach in Baden-Württemberg";
                break;
            case "MQ":
                ausgabe = "Merseburg in Sachsen-Anhalt";
                break; /**Merseburg und Querfurt in Sachsen-Anahlt*/
            case "MR":
                ausgabe = "Marburg in Hessen";
                break;
            case "MS":
                ausgabe = "Münster in Nordrhein-Westfalen";
                break;
            case "MSE":
                ausgabe = "Mecklenburgische Seenplatte in Mecklenburg-Vorpommern";
                break;
            case "MSH":
                ausgabe = "(Landkreis) Mansfeld-Südharz in Sachsen-Anhalt";
                break;
            case "MSP":
                ausgabe = "(Landkreis) Main-Spessart in Bayern";
                break;
            case "MST":
                ausgabe = "Mecklenburg-Strelitz in Mecklenburg-Vorpommern";
                break;
            case "MTK":
                ausgabe = "Main-Taunus-Kreis in Hessen";
                break;
            case "MTL":
                ausgabe = "Muldental in Sachsen";
                break;
            case "MÜ":
                ausgabe = "Mühldorf in Bayern";
                break;
            case "MÜB":
                ausgabe = "Münchberg in Bayern";
                break;
            case "MÜR":
                ausgabe = "Müritz in Mecklenburg-Vorpommern";
                break;
            case "MVL":
                ausgabe = "Mecklenburg-Vorpommerscher Landtag";
                break;
            case "MW":
                ausgabe = "Mittweida in Sachsen";
                break;
            case "MY":
                ausgabe = "Mayen in Rheinland-Pfalz";
                break;
            case "MYK":
                ausgabe = "(Landkreis) Mayen-Koblenz in Rheinland-Pfalz";
                break;
            case "MZ":
                ausgabe = "Mainz in Rheinland-Pfalz";
                break;
            case "MZG":
                ausgabe = "Merzig im Saarland";
                break;
            /** Section N*/
            case "N":
                ausgabe = "Nürnberg in Bayern";
                break;
            case "NAB":
                ausgabe = "Naburg in Bayern";
                break;
            case "NAI":
                ausgabe = "Naila in Bayern";
                break;
            case "NAU":
                ausgabe = "Nauen in Brandenburg";
                break;
            case "NB":
                ausgabe = "Neubrandenburg in Mecklenburg-Vorpommern";
                break;
            case "ND":
                ausgabe = "Neuburg an der Donau in Bayern";
                break;
            case "NDH":
                ausgabe = "Nordhausen in Thüringen";
                break;
            case "NE":
                ausgabe = "Neuss in Nordrhein-Westfalen";
                break;
            case "NEA":
                ausgabe = "Neustadt an der Aisch in Bayern";
                break;
            case "NEB":
                ausgabe = "Nebra in Sachsen-Anhalt";
                break;
            case "NEC":
                ausgabe = "Neunburg in Bayern";
                break;
            case "NEN":
                ausgabe = "Neuunburg in Bayern";
                break;
            case "NES":
                ausgabe = "Neustadt an der Saale in Bayern";
                break;
            case "NEW":
                ausgabe = "Neustadt an der Waldnaab in Bayern";
                break;
            case "NF":
                ausgabe = "Nordfriesland in Schleswig-Holstein";
                break;
            case "NH":
                ausgabe = "Neuhaus in Thüringen";
                break;
            case "NI":
                ausgabe = "Nienburg in Niedersachsen";
                break;
            case "NK":
                ausgabe = "Neunkirchen im Saarland";
                break;
            case "NL":
                ausgabe = "Niedersächsicher Landtag";
                break;
            case "NM":
                ausgabe = "Neumarkt in Bayern";
                break;
            case "NMB":
                ausgabe = "Naumburg in Sachsen-Anhalt";
                break;
            case "NMS":
                ausgabe = "Neumüster in Schleswig-Holstein";
                break;
            case "NÖ":
                ausgabe = "Nördlingen in Bayern";
                break;
            case "NOH":
                ausgabe = "Nordhorn in Niedersachsen";
                break;
            case "NOL":
                ausgabe = "Niederschlesische Oberlausitz in Sachsen";
                break; /**Problem bei Google Maps*/
            case "NOM":
                ausgabe = "Northeim in Niedersachsen";
                break;
            case "NOR":
                ausgabe = "Norden in Niedersachsen";
                break;
            case "NP":
                ausgabe = "Neuruppin in Brandenburg";
                break;
            case "NR":
                ausgabe = "Neuwied am Rhein in Rheinland-Pfalz";
                break;
            case "NRW":
                ausgabe = "Nordrhein-Westfalen(Bundesland)";
                break;
            case "NT":
                ausgabe = "Nürtingen in Baden-Württemberg";
                break;
            case "NU":
                ausgabe = "Neu-Ulm in Bayern";
                break;
            case "NVP":
                ausgabe = "Nordvorpommern in Mecklenburg-Vorpommern";
                break;
            case "NW":
                ausgabe = "Neustadt an der Weinstraße in Rheinland-Pfalz";
                break;
            case "NWM":
                ausgabe = "Nordwestmecklenburg in Mecklenburg-Vorpommern";
                break;
            case "NY":
                ausgabe = "Niesky in Sachsen";
                break;
            case "NZ":
                ausgabe = "Neustrelitz in Mecklenburg-Vorpommern";
                break;
            /** Section O*/
            case "OA":
                ausgabe = "Oberallgäu in Bayern";
                break;
            case "OAL":
                ausgabe = "Ostallgäu in Bayern";
            case "OB":
                ausgabe = "Oberhausen in Nordrhein-Westfalen";
                break;
            case "OBG":
                ausgabe = "Osterbug in Sachsen-Anhalt";
                break;
            case "OC":
                ausgabe = "Oschersleben in Sachsen-Anhalt";
                break;
            case "OCH":
                ausgabe = "Ochsenfurt in Bayern";
                break;
            case "OD":
                ausgabe = "Oldesloe in Schleswig-Holstein";
                break;
            case "OE":
                ausgabe = "Olpe in Nordrhein-Westfalen";
                break;
            case "OF":
                ausgabe = "Offenbach in Hessen";
                break;
            case "OG":
                ausgabe = "Offenburg in Baden-Württemberg";
                break;
            case "OH":
                ausgabe = "Ostholstein in Schleswig-Holstein";
                break;
            case "OHA":
                ausgabe = "Osterode am Harz in Niedersachsen";
                break;
            case "ÖHR":
                ausgabe = "Öhringen in Baden-Württemberg";
                break;
            case "OHV":
                ausgabe = "Oberhavel in Brandenburg";
                break;
            case "OHZ":
                ausgabe = "Osterholz in Niedersachsen";
                break;
            case "OK":
                ausgabe = "Ohrekreis in Sachsen-Anhalt";
                break;
            case "OL":
                ausgabe = "Oldenburg in Niedersachsen";
                break;
            case "OP":
                ausgabe = "Opladen in Nordrhein-Westfalen";
                break;
            case "OPR":
                ausgabe = "Ostprignitz-Ruppin in Brandenburg";
                break;
            case "OS":
                ausgabe = "Osnabrück in Niedersachsen";
                break;
            case "OSL":
                ausgabe = "Oberspreewald-Lausitz in Brandenburg";
                break;
            case "OVI":
                ausgabe = "Oberviechtach in Bayern";
                break;
            case "OVL":
                ausgabe = "Obervogtland in Sachsen";
                break;
            case "OZ":
                ausgabe = "Oschatz in Sachsen";
                break;
            /** Section P*/
            case "P":
                ausgabe = "Potsdam in Brandenburg";
                break;
            case "PA":
                ausgabe = "Passau in Bayern";
                break;
            case "PAF":
                ausgabe = "Pfaffenhofen in Bayern";
                break;
            case "PAN":
                ausgabe = "Pfarrkirchen in Bayern";
                break;
            case "PAR":
                ausgabe = "Parsberg in Bayern";
                break;
            case "PB":
                ausgabe = "Paderborn in Nordrhein-Westfalen";
                break;
            case "PCH":
                ausgabe = "Parchim in Mecklenburg-Vorpommern";
                break;
            case "PE":
                ausgabe = "Peine in Niedersachsen";
                break;
            case "PEG":
                ausgabe = "Pegnitz in Bayern";
                break;
            case "PF":
                ausgabe = "Pforzheim in Baden-Württemberg";
                break;
            case "PI":
                ausgabe = "Pinneberg in Schleswig-Holstein";
                break;
            case "PIR":
                ausgabe = "Pirna in Sachsen";
                break;
            case "PL":
                ausgabe = "Plauen in Sachsen";
                break;
            case "PLÖ":
                ausgabe = "Plön in Schleswig-Holstein";
                break;
            case "PM":
                ausgabe = "Potsdam-Mittelmark in Brandenburg";
                break;
            case "PN":
                ausgabe = "Pößneck in Thüringen";
                break;
            case "PR":
                ausgabe = "Prignitz in Brandenburg";
                break;
            case "PRÜ":
                ausgabe = "Prüm in Rheinland-Pfalz";
                break;
            case "PS":
                ausgabe = "Primasens in Rheinland-Pfalz";
                break;
            case "PW":
                ausgabe = "Pasewalk in Mecklenburg-Vorpommern";
                break;
            case "PZ":
                ausgabe = "Prenzlau in Brandenburg";
                break;
            /**Section Q*/
            case "QFT":
                ausgabe = "Querfurt in Sachsen-Anhalt";
                break;
            case "QLB":
                ausgabe = "Quedlinburg in Sachsen-Anhalt";
                break;
            /**Section R*/
            case "R":
                ausgabe = "Regensburg in Bayern";
                break;
            case "RA":
                ausgabe = "Rastatt in Baden-Württemberg";
                break;
            case "RC":
                ausgabe = "Reichenbach in Sachsen";
                break;
            case "RD":
                ausgabe = "Rendsburg in Schleswig-Holstein";
                break;
            case "RDG":
                ausgabe = "Ribnitz-Damgarten in Mecklenburg-Vorpommern";
                break;
            case "RE":
                ausgabe = "Recklinghausen in Nordrhein-Westfalen";
                break;
            case "REG":
                ausgabe = "Regen in Bayern";
                break;
            case "REH":
                ausgabe = "Rehau in Bayern";
                break;
            case "REI":
                ausgabe = "Reichenhall in Bayern";
                break;
            case "RG":
                ausgabe = "Riesa-Großenhain in Sachsen"; /** Google Maps Problem*/
                break;
            case "RH":
                ausgabe = "Roth in Bayern";
                break;
            case "RI":
                ausgabe = "Rinteln in Niedersachsen";
                break;
            case "RID":
                ausgabe = "Riedenburg in Bayern";
                break;
            case "RIE":
                ausgabe = "Riesa in Sachsen";
                break;
            case "RL":
                ausgabe = "Rochlitz in Sachsen";
                break;
            case "RM":
                ausgabe = "Röbel/Müritz in Mecklenburg-Vorpommern";
                break;
            case "RN":
                ausgabe = "Rathenow in Brandenburg";
                break;
            case "RO":
                ausgabe = "Rosenheim in Bayern";
                break;
            case "ROD":
                ausgabe = "Roding in Bayern";
                break;
            case "ROF":
                ausgabe = "Rotenburg an der Fulda in Hessen";
                break;
            case "ROK":
                ausgabe = "Rockenhausen in Rheinland-Pfalz";
                break;
            case "ROL":
                ausgabe = "Rottenburg an der Laaber in Bayern";
                break;
            case "ROS":
                ausgabe = "Rostock in Mecklenburg-Vorpommern";
                break;
            case "ROT":
                ausgabe = "Rothenburg ob der Tauber in Bayern";
                break;
            case "ROW":
                ausgabe = "Rotenburg(Wümme) in Niedersachsen";
                break;
            case "RP":
                ausgabe = "Rhein-Pfalz in Rheinland-Pfalz";
                break;
            case "RPL":
                ausgabe = "Rheinland-Pfälzischer Landtag";
                break;
            case "RS":
                ausgabe = "Remscheid in Nordrhein-Westfalen";
                break;
            case "RSL":
                ausgabe = "Rosslau in Sachsen-Anhalt";
                break;
            case "RT":
                ausgabe = "Reutlingen in Baden-Württemberg";
                break;
            case "RU":
                ausgabe = "Rudolstadt in Thüringen";
                break;
            case "RÜD":
                ausgabe = "Rüdesheim in Hessen";
                break;
            case "RÜG":
                ausgabe = "Rügen in Mecklenburg-Vorpommern";
                break;
            case "RV":
                ausgabe = "Ravensburg in Baden-Württemberg";
                break;
            case "RW":
                ausgabe = "Rottweil in Baden-Württemberg";
                break;
            case "RZ":
                ausgabe = "Ratzeburg in Schleswig-Holstein";
                break;
            /** Section S*/
            case "S":
                ausgabe = "Stuttgart in Baden-Württemberg";
                break;
            case "SAB":
                ausgabe = "Saarburg in Rheinland-Pfalz";
                break;
            case "SAD":
                ausgabe = "Schwandorf in Bayern";
                break;
            case "SAL":
                ausgabe = "Saarländischer Landtag";
                break;
            case "SAN":
                ausgabe = "Stadtsteinach in Bayern";
                break;
            case "SAW":
                ausgabe = "Salzwedel in Sachsen-Anhalt";
                break;
            case "SB":
                ausgabe = "Saarbrücken in Saarbrücken";
                break;
            case "SBG":
                ausgabe = "Strasburg in Mecklenburg-Vorpommern";
                break;
            case "SBK":
                ausgabe = "Schönebeck in Sachsenanhalt";
                break;
            case "SC":
                ausgabe = "Schwabach in Bayern";
                break;
            case "SCZ":
                ausgabe = "Schleiz in Thüringen";
                break;
            case "SDH":
                ausgabe = "Sondershausen in Thüringen";
                break;
            case "SDL":
                ausgabe = "Stendal in Sachsen-Anhalt";
                break;
            case "SDT":
                ausgabe = "Schwendt in Brandenburg";
                break;
            case "SE":
                ausgabe = "Segeberg in Schleswig-Holstein";
                break;
            case "SEB":
                ausgabe = "Sebnitz in Sachsen";
                break;
            case "SEE":
                ausgabe = "Seelow in Brandenburg";
                break;
            case "SEF":
                ausgabe = "Scheinfeld in Bayern";
                break;
            case "SEL":
                ausgabe = "Selb in Bayern";
                break;
            case "SFB":
                ausgabe = "Senftenberg in Brandenburg";
                break;
            case "SFT":
                ausgabe = "Staßfurt in Sachsen-Anhalt";
                break;
            case "SG":
                ausgabe = "Solingen in Nordrhein-Westfalen";
                break;
            case "SGH":
                ausgabe = "Sangerhausen in Sachsen-Anhalt";
                break;
            case "SH":
                ausgabe = "Schleswig-Holstein (Bundesland)";
                break;
            case "SHA":
                ausgabe = "Schwäbisch Hall in Baden-Württemberg";
                break;
            case "SHG":
                ausgabe = "Stadthagen in Niedersachsen";
                break;
            case "SHK":
                ausgabe = "Saale-Holzland-Kreis in Thüringen";
                break;
            case "SHL":
                ausgabe = "Suhl in Thüringen";
                break;
            case "SI":
                ausgabe = "Siegen in Nordrhein-Westfalen";
                break;
            case "SIG":
                ausgabe = "Sigmaringen in Baden-Württemberg";
                break;
            case "SIM":
                ausgabe = "Simmern in Rheinland-Pfalz";
                break;
            case "SK":
                ausgabe = "Saalekreis in Sachsen-Anhalt";
                break;
            case "SL":
                ausgabe = "Schleswig in Schleswig-Holstein";
                break;
            case "SLE":
                ausgabe = "Schleiden in Nordrhein-Westfalen";
                break;
            case "SLF":
                ausgabe = "Saalfeld in Thürigen";
                break;
            case "SLK":
                ausgabe = "Salzlandkreis in Sachsen-Anhalt";
                break;
            case "SLN":
                ausgabe = "Schmölln in Thüringen";
                break;
            case "SLS":
                ausgabe = "Saarlouis im Saarland";
                break;
            case "SLÜ":
                ausgabe = "Schlüchtern in Hessen";
                break;
            case "SLZ":
                ausgabe = "Salzungen in Thüringen";
                break;
            case "SM":
                ausgabe = "Schmalkalden in Thüringen";
                break;
            case "SMÜ":
                ausgabe = "Schwabmünchen in Bayern";
                break;
            case "SN":
                ausgabe = "Schwerin in Mecklenburg-Vorpommern";
                break;
            case "SO":
                ausgabe = "Soest in Nordrhein-Westfalen";
                break;
            case "SOB":
                ausgabe = "Schrobenhausen in Bayern";
                break;
            case "SOG":
                ausgabe = "Schongau in Bayern";
                break;
            case "SOK":
                ausgabe = "Saale-Orla-Kreis in Thüringen";
                break;
            case "SÖM":
                ausgabe = "Sömmerda in Thüringen";
                break;
            case "SON":
                ausgabe = "Sonneberg in Thüringen";
                break;
            case "SP":
                ausgabe = "Speyer in Rheinland-Pfalz";
                break;
            case "SPB":
                ausgabe = "Spremberg in Brandenburg";
                break;
            case "SPN":
                ausgabe = "Spree-Neiße in Brandenburg";
                break;
            case "SR":
                ausgabe = "Straubing in Bayern";
                break;
            case "SRB":
                ausgabe = "Strausberg in Brandenburg";
                break;
            case "SRO":
                ausgabe = "Stadtroda in Thüringen";
                break;
            case "ST":
                ausgabe = "Steinfurt in Nordrhein-Westfalen";
                break;
            case "STA":
                ausgabe = "Starnberg in Bayern";
                break;
            case "STB":
                ausgabe = "Stemberg in Mecklenburg-Vorpommern";
                break;
            case "STD":
                ausgabe = "Stade in Niedersachsen";
                break;
            case "STE":
                ausgabe = "Staffelstein in Bayern";
                break;
            case "STL":
                ausgabe = "Stoilberg in Sachsen";
                break;
            case "SU":
                ausgabe = "Siegburg in Nordrhein-Westfalen";
                break;
            case "SUL":
                ausgabe = "Sulzbach in Bayern";
                break;
            case "SÜW":
                ausgabe = "Südliche Weinstraße in Rheinland-Pfalz";
                break;
            case "SW":
                ausgabe = "Schweinfurt in Bayern";
                break;
            case "SWA":
                ausgabe = "Schwalbach in Hessen";
                break;
            case "SZ":
                ausgabe = "Salzgitter in Niedersachsen";
                break;
            case "SZB":
                ausgabe = "Schwarzenberg in Sachsen";
                break;
            /** Section T*/
            case "TBB":
                ausgabe = "Tauberbischofsheim in Baden-Württemberg";
                break;
            case "TDO":
                ausgabe = "Torgau, Delitzsch und Oschat in Sachsen"; //Probleme bei Maps
                break;
            case "TE":
                ausgabe = "Tecklenburg in Nordrhein-Westfalen";
                break;
            case "TET":
                ausgabe = "Teterow in Mecklenburg-Vorpommern";
                break;
            case "TF":
                ausgabe = "Teltow Fläming in Brandenburg";
                break;
            case "TG":
                ausgabe = "Torgau in Sachsen";
                break;
            case "THL":
                ausgabe = "Thüringer Landtag";
                break;
            case "THW":
                ausgabe = "Technisches Hilfswerk"; //evtl. Probleme bei Maps
                break;
            case "TIR":
                ausgabe = "Tirschenreuth in Bayern";
                break;
            case "TO":
                ausgabe = "Torgau, Oschatz in Sachsen"; //Problem bei Maps
                break;
            case "TÖL":
                ausgabe = "Tölz in Bayern";
                break;
            case "TP":
                ausgabe = "Templin in Brandenburg";
                break;
            case "TR":
                ausgabe = "Trier in Rheinland-Pfalz";
                break;
            case "TS":
                ausgabe = "Taraunstein in Bayern";
                break;
            case "TÜ":
                ausgabe = "Tübingen in Baden-Württemberg";
                break;
            case "TUT":
                ausgabe = "Tuttlingen in Baden-Württemberg";
                break;
            /**Section U */
            case "UE":
                ausgabe = "Uelzen in Niedersachsen";
                break;
            case "UEM":
                ausgabe = "Ueckermünde in Mecklenburg-Vorpommern";
                break;
            case "UFF":
                ausgabe = "Uffenheim in Bayern";
                break;
            case "UH":
                ausgabe = "Unstrut Hainich in Thüringen";
                break;
            case "UL":
                ausgabe = "Ulm in Baden-Württemberg";
                break;
            case "UM":
                ausgabe = "Uckermark in Brandenburg";
                break;
            case "UN":
                ausgabe = "Unna in Nordrhein-Westfalen";
                break;
            case "USI":
                ausgabe = "Usingen in Hessen";
                break;
            /** Section V*/
            case "V":
                ausgabe = "Vogtland in Sachsen";
                break;
            case "VAI":
                ausgabe = "Vaihingen in Baden-Württemberg";
                break;
            case "VB":
                ausgabe = "Vogelsberg in Hessen";
                break;
            case "VEC":
                ausgabe = "Vechta in Niedersachsen";
                break;
            case "VER":
                ausgabe = "Verden in Niedersachsen";
                break;
            case "VG":
                ausgabe = "Vorpommern Greifswald in Mecklenburg-Vorpommern";
                break;
            case "VIB":
                ausgabe = "Vilsbiburg in Bayern";
                break;
            case "VIE":
                ausgabe = "Viersen in Nordrhein-Westfalen";
                break;
            case "VK":
                ausgabe = "Völkingen im Saarland";
                break;
            case "VOH":
                ausgabe = "Vohenstrauß in Bayern";
                break;
            case "VR":
                ausgabe = "Vorpommern Rügen in Mecklenburg-Vorpommern";
                break;
            case "VS":
                ausgabe = "Villingen-Schwenningen in Baden-Württemberg";
                break;
            /** Section W*/
            case "W":
                ausgabe = "Wuppertal in Nordrhein-Westfalen";
                break;
            case "WA":
                ausgabe = "Waldeck in Hessen";
                break;
            case "WAF":
                ausgabe = "Warendorf in Nordrhein-Westfalen";
                break;
            case "WAK":
                ausgabe = "Wartburgkreis in Thüringen";
                break;
            case "WAN":
                ausgabe = "Wanne in Nordrhein-Westfalen";
                break;
            case "WAT":
                ausgabe = "Wattenscheid in Nordrhein-Westfalen";
                break;
            case "WB":
                ausgabe = "Wittenberg in Sachsen-Anhalt";
                break;
            case "WBS":
                ausgabe = "Worbis in Thüringen";
                break;
            case "WDA":
                ausgabe = "Werdau in Sachsen";
                break;
            case "WE":
                ausgabe = "Weimar in Thüringen";
                break;
            case "WEL":
                ausgabe = "Weilburg in Hessen";
                break;
            case "WEN":
                ausgabe = "Weiden in Bayern";
                break;
            case "WER":
                ausgabe = "Wertingen in Bayern";
                break;
            case "WES":
                ausgabe = "Wesel in Nordrhein-Westfalen";
                break;
            case "WF":
                ausgabe = "Wolfenbüttel in Niedersachsen";
                break;
            case "WHV":
                ausgabe = "Wilhelmshaven in Niedersachsen";
                break;
            case "WI":
                ausgabe = "Wiesbaden in Hessen";
                break;
            case "WIL":
                ausgabe = "Wittlich in Rheinland-Pfalz";
                break;
            case "WIS":
                ausgabe = "Wismar in Mecklenburg-Vorpommern";
                break;
            case "WIT":
                ausgabe = "Witten in Nordrhein-Westfalen";
                break;
            case "WIZ":
                ausgabe = "Witzenhausen in Hessen";
                break;
            case "WK":
                ausgabe = "Wittstock in Brandenburg";
                break;
            case "WL":
                ausgabe = "Winsen(Luhe) in Niedersachsen";
                break;
            case "WLG":
                ausgabe = "Wolgast in Mecklenburg-Vorpommern";
                break;
            case "WM":
                ausgabe = "Weilheim in Bayern";
                break;
            case "WMS":
                ausgabe = "Wolmirstedt in Sachsen-Anhalt";
                break;
            case "WN":
                ausgabe = "Waiblingen in Baden-Württemberg";
                break;
            case "WND":
                ausgabe = "Wendel im Saarland";
                break;
            case "WO":
                ausgabe = "Worms in Rheinland-Pfalz";
                break;
            case "WOB":
                ausgabe = "Wolfsburg in Niedersachsen";
                break;
            case "WOH":
                ausgabe = "Wolfhagen in Hessen";
                break;
            case "WOL":
                ausgabe = "Wolfach in Baden-Württemberg";
                break;
            case "WOR":
                ausgabe = "Wolfratshausen in Bayern";
                break;
            case "WOS":
                ausgabe = "Wolfstein in Bayern";
                break;
            case "WR":
                ausgabe = "Wernigerode in Sachsen-Anhalt";
                break;
            case "WRN":
                ausgabe = "Waren in Mecklenburg-Vorpommern";
                break;
            case "WS":
                ausgabe = "Wasserburg in Bayern";
                break;
            case "WSF":
                ausgabe = "Weissenfels in Sachsen-Anhalt";
                break;
            case "WST":
                ausgabe = "Westerstede in Niedersachsen";
                break;
            case "WSW":
                ausgabe = "Weisswasser in Sachsen";
                break;
            case "WT":
                ausgabe = "Waldshut in Baden-Württemberg";
                break;
            case "WTM":
                ausgabe = "Wittmund in Niedersachsen";
                break;
            case "WÜ":
                ausgabe = "Würzburg in Bayern";
                break;
            case "WUG":
                ausgabe = "Weißenburg in Bayern";
                break;
            case "WÜM":
                ausgabe = "Waldmünchen in Bayern";
                break;
            case "WUN":
                ausgabe = "Wunsiedel in Bayern";
                break;
            case "WUR":
                ausgabe = "Wurzen in Sachsen";
                break;
            case "WW":
                ausgabe = "Westerwald in Rheinland-Pfalz";
                break;
            case "WZ":
                ausgabe = "Wetzlar in Hessen";
                break;
            case "WZL":
                ausgabe = "Wanzleben in Sachsen-Anhalt";
                break;
            /** Section X,Y,Z*/
            case "X":
                ausgabe = "NATO";
                break;
            case "Y":
                ausgabe = "Bundeswehr";
                break;
            case "Z":
                ausgabe = "Zwickau in Sachsen";
                break;
            case "ZE":
                ausgabe = "Zerbst in Sachsen-Anhalt";
                break;
            case "ZEL":
                ausgabe = "Zeli Rheinland-Pfalz";
                break;
            case "ZI":
                ausgabe = "Zittau in Sachsen";
                break;
            case "ZIG":
                ausgabe = "Ziegenhain in Hessen";
                break;
            case "ZP":
                ausgabe = "Zschopau in Sachsen";
                break;
            case "ZR":
                ausgabe = "Zeulenroda in Thüringen";
                break;
            case "ZW":
                ausgabe = "Zweibrücken in Rheinland-Pfalz";
                break;
            case "ZZ":
                ausgabe = "Zeitz in Sachsen-Anhalt";
                break;
            default: ausgabe = "Es gibt (noch) kein solches deutsches Kennzeichen!";
                break;
        }

            textView.setText(ausgabe);
        if(ausgabe!= "Es gibt (noch) kein solches deutsches Kennzeichen!" && Einstellungen.history_enabled) {
            //Wenn beim Verlauf keine Einträge mehrfach vorkommen sollen, hier noch ergänzen (siehe CustomAdapter)
            // evtl. als Einstellungsmöglichkeit umsetzbar
            /**for(int count = 0; count<i; count++){

                if(speicher.getString(String.valueOf(count),"")==ausgabe){

                }
            }
            speicher.getString()*/
            editor.putString(i.toString(), eingabe + " - " + ausgabe);
            editor.commit();
            i++;
            editor2.putInt("counter", i);
            editor2.commit();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if(Einstellungen.maps_enabled) {
        Geocoder coder = new Geocoder(this);
        LatLng p1 = null;

        // May throw an IOException
        try {
            List<Address> address = coder.getFromLocationName(ausgabe, 5);
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());


            map.addMarker(new MarkerOptions()
                    .position(p1)
                    .title("Marker"));
            int zoom = Einstellungen.maps_zoom;
            CameraUpdate cameraUpdate;
            if(zoom==10){
                cameraUpdate = CameraUpdateFactory.newLatLngZoom(p1, zoom);
            } else {
                cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(51.163375, 10.447683), zoom);
            }
            map.animateCamera(cameraUpdate);
        } catch (Exception e) {


            if (ausgabe != "Es gibt (noch) kein solches deutsches Kennzeichen!") {
                TextView textView = (TextView) findViewById(R.id.ausgabe2);
                textView.setText(ausgabe + " (Kartenansicht nicht möglich)");
            }
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(51.163375, 10.447683), 5);
            map.animateCamera(cameraUpdate);
        }
        } else {
            if (ausgabe != "Es gibt (noch) kein solches deutsches Kennzeichen!") {
                TextView textView = (TextView) findViewById(R.id.ausgabe2);
                textView.setText(ausgabe += " (Kartenansicht deaktiviert)");
            }
        }
    }
}



