class ProcessCompartorSetQueue implements
      java.util.Comparator<Process> {
  @Override
  public int compare(Process s1, Process s2) {
    return s1.getQueueTime() - s2.getQueueTime();
  }
}