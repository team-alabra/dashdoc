using Dashdoc.API.Domain.Enums;

namespace Dashdoc.API.Domain.Models;

public class Provider
{
    public long Id { get; init; }
    public long? AgencyId { get; init; }
    public string? FirstName { get; init; } 
    public string? LastName { get; init; }
    public ProviderType? Type { get; init; }
    public Agency? Agency { get; init; }
    public Gender Gender { get; init; }
    public string? City { get; init; }
    public string? ZipCode { get; init; }
    public State State { get; init; }
    public DateTime? BirthDate { get; init; }
    public Discipline Discipline  { get; init; }
}