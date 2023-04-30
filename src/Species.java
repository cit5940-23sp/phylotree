public class Species {

    private 
        String genusName;
        String specificEpithet;
        String sequence;
    
    public String getGenusName() {
        return this.genusName;
    }

    public String getSpecificEpithet() {
        return this.specificEpithet;
    }

    public String getSequence() {
        return this.sequence;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public void setSpecificEpithet(String specificEpithet) {
        this.specificEpithet = specificEpithet;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Species)) {
            return false;
        }

        Species other = (Species) o;

        if (this.getGenusName().equals(other.getGenusName()) &&
                this.getSpecificEpithet().equals(other.getSpecificEpithet()) &&
                this.getSequence().equals(other.getSequence())) {
            return true;
        }
    }





}
