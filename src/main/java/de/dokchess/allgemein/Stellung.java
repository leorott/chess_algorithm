/*
 * Copyright (c) 2010-2021 Stefan Zoerner
 *
 * This file is part of DokChess.
 *
 * DokChess is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DokChess is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DokChess.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.dokchess.allgemein;

import java.util.*;

import static de.dokchess.allgemein.Farbe.SCHWARZ;
import static de.dokchess.allgemein.Farbe.WEISS;
import static de.dokchess.allgemein.Felder.*;
import static de.dokchess.allgemein.FigurenArt.KOENIG;
import static de.dokchess.allgemein.FigurenArt.TURM;
import static de.dokchess.allgemein.RochadeRecht.*;

/**
 * Beschreibt eine Spielsituation. Hierzu zaehlen die Figuren auf dem
 * Brett(Stellung), die Farbe am Zug, Rochaderechte usw.
 *
 * @author StefanZ
 */
public final class Stellung {

    private static final int ANZAHL_REIHEN = 8;
    private static final int ANZAHL_LINIEN = 8;

    private Farbe amZug;

    private Figur[][] brett;

    private Feld enPassantFeld;

    private Set<RochadeRecht> rochadeRechte;

    /**
     * Erzeugt die Anfangsstellung. Weiss ist am Zug.
     */
    public Stellung() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    /**
     * Erzeugt die ueber die Forsyth-Edwards-Notation (FEN beschriebene
     * Stellung.
     *
     * @param fen Stellung als zeichenkette in FEN-Notation
     */
    public Stellung(final String fen) {
        this.brett = new Figur[ANZAHL_REIHEN][ANZAHL_LINIEN];
        ForsythEdwardsNotation.fromString(this, fen);
    }

    Stellung(Stellung s) {
        this.amZug = s.amZug;
        this.rochadeRechte = s.rochadeRechte;
        this.enPassantFeld = s.enPassantFeld;
        this.brett = new Figur[ANZAHL_REIHEN][];
        System.arraycopy(s.brett, 0, this.brett, 0, ANZAHL_REIHEN);
    }

    /**
     * Liefert zurueck, wer am Zuge ist.
     *
     * @return schwarz oder weiss
     */
    public Farbe getAmZug() {
        return amZug;
    }

    void setAmZug(Farbe amZug) {
        this.amZug = amZug;
    }

    /**
     * Liefert die Figur auf dem Feld mit den gegebenen Koordinaten zur&uuml;ck,
     * oder null, falls es frei ist.
     *
     * @param reihe Reihe des Feldes
     * @param linie Linie des Feldes
     * @return die Figur, oder null falls das Feld frei ist.
     */
    public Figur getFigur(int reihe, int linie) {
        return brett[reihe][linie];
    }

    void setFigur(int reihe, int linie, Figur figur) {
        brett[reihe][linie] = figur;
    }

    /**
     * Liefert die Figur auf dem Feld zur&uuml;ck, oder null, falls es frei ist.
     *
     * @param feld das Feld
     * @return die Figur, oder null falls das Feld frei ist.
     */
    public Figur getFigur(Feld feld) {
        return brett[feld.getReihe()][feld.getLinie()];
    }

    void setFigur(Feld feld, Figur figur) {
        brett[feld.getReihe()][feld.getLinie()] = figur;
    }

    public Feld getEnPassantFeld() {
        return enPassantFeld;
    }

    void setEnPassantFeld(Feld enPassantFeld) {
        this.enPassantFeld = enPassantFeld;
    }

    public Set<RochadeRecht> getRochadeRechte() {
        return rochadeRechte;
    }

    void setRochadeRechte(Set<RochadeRecht> rochadeRechte) {
        this.rochadeRechte = rochadeRechte;
    }

    /**
     * Liefert eine Liste mit allen Felder zur&uuml;ck, auf denen die
     * gesuchte Figur steht. Falls die Figur gar nicht auf dem Brett steht,
     * wird eine leere Liste zur&uuml;ckgeliefert.
     *
     * @param figur gesuchte Figur
     * @return Liste mit Feldern, auf denen eine solche Figur steht.
     */
    public List<Feld> findeFelderMit(final Figur figur) {
        List<Feld> felder = new ArrayList<>();

        for (int reihe = 0; reihe < ANZAHL_REIHEN; ++reihe) {
            for (int linie = 0; linie < ANZAHL_LINIEN; ++linie) {
                Figur aktFigur = getFigur(reihe, linie);
                if (aktFigur != null && aktFigur.equals(figur)) {
                    felder.add(new Feld(reihe, linie));
                }
            }
        }

        return felder;
    }

    /**
     * Findet das Feld mit dem K&ouml;nig in der betreffenden Farbe.
     *
     * @param farbe Farbe des gesuchten K&ouml;nigs.
     * @return Feld mit dem K&ouml;nig, oder null, falls er fehlt
     */
    public Feld findeFeldMitKoenig(final Farbe farbe) {
        for (int reihe = 0; reihe < ANZAHL_REIHEN; ++reihe) {
            for (int linie = 0; linie < ANZAHL_LINIEN; ++linie) {
                Figur aktFigur = getFigur(reihe, linie);
                if (aktFigur != null && aktFigur.ist(KOENIG)
                        && aktFigur.ist(farbe)) {
                    return new Feld(reihe, linie);
                }
            }
        }
        return null;
    }

    public Set<Feld> felderMitFarbe(final Farbe farbe) {
        HashSet<Feld> felder = new HashSet<Feld>();

        for (int reihe = 0; reihe < ANZAHL_REIHEN; ++reihe) {
            for (int linie = 0; linie < ANZAHL_LINIEN; ++linie) {
                Figur aktFigur = getFigur(reihe, linie);
                if (aktFigur != null && aktFigur.ist(farbe)) {
                    felder.add(new Feld(reihe, linie));
                }
            }
        }

        return felder;
    }

    public Stellung fuehreZugAus(Zug zug) {
        Stellung neueStellung = kopiereStellungMitVomZugBetroffenenReihen(this,
                zug);

        // Ziehen
        neueStellung.setFigur(zug.getVon(), null);
        if (zug.istUmwandlung()) {
            // Umwandlung des Bauern in neue Figur
            neueStellung.setFigur(zug.getNach(),
                    new Figur(zug.getNeueFigurenart(), amZug));
        } else {
            neueStellung.setFigur(zug.getNach(), zug.getFigur());
        }

        // En Passant Feld setzen
        if (zug.istBauernZugZweiVor()) {
            int delta = zug.getFigur().ist(WEISS) ? -1 : +1;
            neueStellung.enPassantFeld = new Feld(zug.getVon().getReihe()
                    + delta, zug.getVon().getLinie());
        } else {
            neueStellung.enPassantFeld = null;
        }

        if (zug.istRochade()) {
            rochadeDurchfuehrenBehandeln(zug, neueStellung);
        } else {
            rochadeRechteKorrigieren(zug, neueStellung);
        }

        neueStellung.amZug = this.amZug.andereFarbe();

        return neueStellung;
    }

    /**
     * Liefert zurueck, ob die betreffende Rochade noch zulaessig waere.
     *
     * @param rr konkrete Rochade, zum Beispiel WEISS_LANG
     * @return true, fals grundsaetzlich noch erlaubt
     */
    public boolean rochadeErlaubt(final RochadeRecht rr) {
        return rochadeRechte.contains(rr);
    }

    private void rochadeDurchfuehrenBehandeln(Zug zug, Stellung neueStellung) {
        if (zug.istRochadeKurz()) {
            neueStellung.rochadeRechte = EnumSet.copyOf(rochadeRechte);
            switch (getAmZug()) {
                case WEISS:
                    neueStellung.setFigur(h1, null);
                    neueStellung.setFigur(f1, new Figur(TURM, WEISS));
                    neueStellung.rochadeRechte.remove(WEISS_KURZ);
                    neueStellung.rochadeRechte.remove(WEISS_LANG);
                    break;
                case SCHWARZ:
                    neueStellung.setFigur(h8, null);
                    neueStellung.setFigur(f8, new Figur(TURM, SCHWARZ));
                    neueStellung.rochadeRechte.remove(SCHWARZ_KURZ);
                    neueStellung.rochadeRechte.remove(SCHWARZ_LANG);
                    break;
            }
        } else if (zug.istRochadeLang()) {
            neueStellung.rochadeRechte = EnumSet.copyOf(rochadeRechte);
            switch (getAmZug()) {
                case WEISS:
                    neueStellung.setFigur(a1, null);
                    neueStellung.setFigur(d1, new Figur(TURM, WEISS));
                    neueStellung.rochadeRechte.remove(WEISS_KURZ);
                    neueStellung.rochadeRechte.remove(WEISS_LANG);
                    break;
                case SCHWARZ:
                    neueStellung.setFigur(a8, null);
                    neueStellung.setFigur(d8, new Figur(TURM, SCHWARZ));
                    neueStellung.rochadeRechte.remove(SCHWARZ_KURZ);
                    neueStellung.rochadeRechte.remove(SCHWARZ_LANG);
                    break;
            }
        }
    }

    private void rochadeRechteKorrigieren(Zug zug, Stellung neueStellung) {

        // Ggf. Rochaderechte loeschen
        if (rochadeRechte.size() > 0) {
            if (zug.getFigur().ist(KOENIG)) {
                neueStellung.rochadeRechte = EnumSet.copyOf(rochadeRechte);
                switch (amZug) {
                    case WEISS:
                        neueStellung.rochadeRechte.remove(WEISS_KURZ);
                        neueStellung.rochadeRechte.remove(WEISS_LANG);
                        break;
                    case SCHWARZ:
                        neueStellung.rochadeRechte.remove(SCHWARZ_KURZ);
                        neueStellung.rochadeRechte.remove(SCHWARZ_LANG);
                        break;
                }
            } else if (zug.getFigur().ist(TURM)) {
                neueStellung.rochadeRechte = EnumSet.copyOf(rochadeRechte);
                switch (amZug) {
                    case WEISS:
                        if (zug.getVon().equals(a1)) {
                            neueStellung.rochadeRechte.remove(WEISS_LANG);
                        } else if (zug.getVon().equals(h1)) {
                            neueStellung.rochadeRechte.remove(WEISS_KURZ);
                        }
                        break;
                    case SCHWARZ:
                        if (zug.getVon().equals(a8)) {
                            neueStellung.rochadeRechte.remove(SCHWARZ_LANG);
                        } else if (zug.getVon().equals(h8)) {
                            neueStellung.rochadeRechte.remove(SCHWARZ_KURZ);
                        }
                        break;
                }
            }
        }
    }

    private Stellung kopiereStellungMitVomZugBetroffenenReihen(Stellung quelle,
                                                               Zug zug) {
        Stellung neueStellung = new Stellung(quelle);

        int reihe1 = zug.getVon().getReihe();
        int reihe2 = zug.getNach().getReihe();

        neueStellung.brett[reihe1] = new Figur[ANZAHL_LINIEN];
        System.arraycopy(quelle.brett[reihe1], 0, neueStellung.brett[reihe1],
                0, ANZAHL_LINIEN);
        if (reihe2 != reihe1) {
            neueStellung.brett[reihe2] = new Figur[ANZAHL_LINIEN];
            System.arraycopy(quelle.brett[reihe2], 0,
                    neueStellung.brett[reihe2], 0, ANZAHL_LINIEN);
        }

        return neueStellung;
    }

    /**
     * Liefert die Stellung in der Forsyth Edwards Notation (FEN) zurueck.
     */
    @Override
    public String toString() {
        return ForsythEdwardsNotation.toString(this);
    }
}
