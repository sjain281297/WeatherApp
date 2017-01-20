package com.example.shubhamjain.mapsapp;

/**
 * Created by SHUBHAM JAIN on 02-09-2016.
 */
public class PlannerResponse {
    Trip trip;


    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public static class Trip{
        Chance chance_of;
        TempHigh temp_high;
        TempLow temp_low;

        public Chance getChance_of() {
            return chance_of;
        }

        public void setChance_of(Chance chance_of) {
            this.chance_of = chance_of;
        }

        public TempHigh getTemp_high() {
            return temp_high;
        }

        public void setTemp_high(TempHigh temp_high) {
            this.temp_high = temp_high;
        }

        public TempLow getTemp_low() {
            return temp_low;
        }

        public void setTemp_low(TempLow temp_low) {
            this.temp_low = temp_low;
        }

        public static class TempHigh{
             Average avg;

            public Average getAvg() {
                return avg;
            }

            public void setAvg(Average avg) {
                this.avg = avg;
            }

            public static class Average{
                String C;

                public String getC() {
                    return C;
                }

                public void setC(String c) {
                    C = c;
                }
            }

        }

        public static class TempLow{
            Average avg;

            public Average getAvg() {
                return avg;
            }

            public void setAvg(Average avg) {
                this.avg = avg;
            }

            public static class Average{
                String C;

                public String getC() {
                    return C;
                }

                public void setC(String c) {
                    C = c;
                }
            }
        }

        public static class Chance{
            SultryDay chanceofsultryday;
            HumidDay chanceofhumidday;
            PrecipDay chanceofprecip;
            ThunderDay chanceofthunderday;


            public SultryDay getChanceofsultryday() {
                return chanceofsultryday;
            }

            public void setChanceofsultryday(SultryDay chanceofsultryday) {
                this.chanceofsultryday = chanceofsultryday;
            }

            public HumidDay getChanceofhumidday() {
                return chanceofhumidday;
            }

            public void setChanceofhumidday(HumidDay chanceofhumidday) {
                this.chanceofhumidday = chanceofhumidday;
            }

            public PrecipDay getChanceofprecip() {
                return chanceofprecip;
            }

            public void setChanceofprecip(PrecipDay chanceofprecip) {
                this.chanceofprecip = chanceofprecip;
            }

            public ThunderDay getChanceofthunderday() {
                return chanceofthunderday;
            }

            public void setChanceofthunderday(ThunderDay chanceofthunderday) {
                this.chanceofthunderday = chanceofthunderday;
            }

            public static class ThunderDay{
                String name;
                String percentage;


                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }

            public static class PrecipDay{
                String name;
                String percentage;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }

            public static class SultryDay{
                String name;
                String percentage;
                String description;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }

            public static class HumidDay{
                String name;
                String percentage;
                String description;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }
        }
    }
}
