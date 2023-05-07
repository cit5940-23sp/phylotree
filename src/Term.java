public class Term {

    private String term;
    private Species spec;

    /**
     * Initialize a Term with a given query String and weight
     */
    public Term(String term, Species spec) throws IllegalArgumentException {
        if (term == null) {
            throw new IllegalArgumentException("Invalid arguments. Term must be " +
                    "non-null and weight must be non-negative.");
        }

        this.term = term;
        this.spec = spec;
    }

    public int compareTo(Term that) {
        return this.term.compareTo(that.getTerm());
    }

    @Override
    public String toString() {
        if (spec != null) {
            return this.spec.toString();
        } else {
            return "null";
        }

    }

    public Species getSpec() {
        return spec;
    }

    public String getTerm() {
        return term;
    }

    public void setSpec(Species species) {
        this.spec = species;
    }

    public String setTerm(String term) {
        this.term = term;
        return this.term;
    }


}
